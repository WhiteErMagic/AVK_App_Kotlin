package com.developmentavk.avk_app_kotlin.presentation.main

import android.app.ActivityManager
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developmentavk.avk_app_kotlin.R
import com.developmentavk.avk_app_kotlin.app.TheApplication
import com.developmentavk.avk_app_kotlin.app.utils.const.ConstData
import com.developmentavk.avk_app_kotlin.databinding.FragmentMainWindowBinding
import com.developmentavk.avk_app_kotlin.domain.lunch.LunchService
import com.developmentavk.avk_app_kotlin.domain.navigator
import com.developmentavk.avk_app_kotlin.presentation.ViewModelFactory
import com.developmentavk.avk_app_kotlin.presentation.suppliers.ListAllSuppliersFragment
import com.developmentavk.avk_app_kotlin.presentation.suppliers.PlanSuppliersFragment
import com.developmentavk.avk_app_kotlin.utils.common.CommonFuns
import javax.inject.Inject

class MainWindowFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentMainWindowBinding? = null
    private val binding get() = _binding!!
    private var progressDialog: AlertDialog? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[MainWindowViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as TheApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainWindowBinding.inflate(inflater, container, false)

        binding.release.text = "Rel: "+ ConstData.RELEASE

        initListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.error.observe(viewLifecycleOwner){error ->
            progressDialog?.dismiss()
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        viewModel.loginResult.observe(viewLifecycleOwner){
            progressDialog?.dismiss()
        }

        viewModel.name.observe(viewLifecycleOwner){name ->
            binding.txtWelcome.text = name
        }

        viewModel.btnLunchTxt.observe(viewLifecycleOwner){lunch ->
            binding.btnLunch.text = activity?.resources?.getString(R.string.left) + lunch.toString()
        }

        viewModel.fillData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.btnLunch.setOnClickListener(this)
        binding.btnMyPlan.setOnClickListener(this)
        binding.btnAll.setOnClickListener(this)
        binding.btnOnBoard.setOnClickListener(this)
        binding.cardExchange.setOnClickListener(this)
        binding.cardZebra.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.btnLunch -> { activity?.let{startLunchService() }}
            binding.btnMyPlan -> { navigator().goToNext(PlanSuppliersFragment()) }
            binding.btnAll -> { navigator().goToNext(ListAllSuppliersFragment())}
            binding.cardExchange -> { startExchange() }
        }
    }

    private fun startExchange() {
        progressDialog = CommonFuns.setProgressDialogFabrica("Получение данных...", requireContext())
        progressDialog!!.show()
        viewModel.startExchange()
    }

    private fun startLunchService() {
        activity?.let {
            var alreadyStarted = false
            val activityManager: ActivityManager =
                activity!!.getSystemService(ACTIVITY_SERVICE) as ActivityManager
            @Suppress("DEPRECATION")
            val services: List<ActivityManager.RunningServiceInfo> =
                activityManager.getRunningServices(50)
            for (temp in services) {
                if (temp.service.className == LunchService::class.java.name)
                    alreadyStarted = true
            }
            if (!alreadyStarted) {
                val intent = Intent(context, LunchService::class.java).apply {
                    putExtra(
                        LunchService.LUNCH_TIME,
                        60
                    )
                }
                activity!!.bindService(intent, serviceConnect, Context.BIND_AUTO_CREATE)
            } else {
                activity!!.unbindService(serviceConnect)
            }
        }
    }

    private val serviceConnect = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = (service as? LunchService.LocalBinder) ?: return
            val foregroundService = binder.getService()
            foregroundService.onLunchTimeChanged = { lunchTime ->
                viewModel.setBtnLunchTxt(lunchTime)
            }
            foregroundService.startLunch()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
}