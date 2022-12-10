package com.developmentavk.avk_app_kotlin.presentation.login

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developmentavk.avk_app_kotlin.app.TheApplication
import com.developmentavk.avk_app_kotlin.databinding.FragmentLoginBinding
import com.developmentavk.avk_app_kotlin.domain.navigator
import com.developmentavk.avk_app_kotlin.presentation.ViewModelFactory
import com.developmentavk.avk_app_kotlin.presentation.main.MainWindowFragment
import com.developmentavk.avk_app_kotlin.utils.common.CommonFuns
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding?: throw RuntimeException("FragmentFirstBinding = null")
    private var progressDialog: AlertDialog? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as TheApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            progressDialog = CommonFuns.setProgressDialogFabrica("Авторизация...", requireContext())
            progressDialog!!.show()
            viewModel.login(binding.userlogin.text.toString(), binding.password.text.toString())
        }

        viewModel.fillLogin()

        viewModel.userProfileLD.observe(viewLifecycleOwner){userProfile ->
            binding.userlogin.setText(userProfile.login)
            binding.password.setText(userProfile.pass)
        }

        viewModel.error.observe(viewLifecycleOwner){error ->
            progressDialog?.dismiss()
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

        viewModel.loginResult.observe(viewLifecycleOwner){
            progressDialog?.dismiss()
            navigator().goToNext(MainWindowFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}