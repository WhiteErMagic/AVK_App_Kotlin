package com.developmentavk.avk_app_kotlin.presentation.goods

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developmentavk.avk_app_kotlin.R
import com.developmentavk.avk_app_kotlin.app.TheApplication
import com.developmentavk.avk_app_kotlin.databinding.FragmentListGoodsBinding
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList
import com.developmentavk.avk_app_kotlin.domain.navigator
import com.developmentavk.avk_app_kotlin.presentation.ViewModelFactory
import javax.inject.Inject


class ListGoodsFragment : Fragment(), GoodAdapter.OnClickToSupplier {
    private var _binding: FragmentListGoodsBinding? = null
    private val binding get() = _binding!!
    private var adapter: GoodAdapter? = null
    private var supplier:SupplierObjectForList? = null
    lateinit var filter:IntentFilter

    companion object{
        private const val SUPPLIER = "Supplier"
        fun getInstance(supplier: SupplierObjectForList): ListGoodsFragment{
            return ListGoodsFragment().apply{
                arguments = Bundle().apply { putParcelable(SUPPLIER, supplier)}
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[ListGoodsViewModel::class.java]
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
        arguments?.let {
            supplier = it.getParcelable<SupplierObjectForList>(SUPPLIER)
        }
        filter = IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(resources.getString(R.string.activity_intent_filter_action));
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val inflaterAsync = AsyncLayoutInflater(requireContext())
//        inflaterAsync?.inflate(R.layout.large_layout, null) { view, resid, parent ->
//            (v as? ViewGroup)?.addView(view) // add large view to already inflated view
//             progress bar
//        }


        //_binding = FragmentListGoodsBinding.inflate(inflater, container, false)
        _binding = FragmentListGoodsBinding.inflate(inflater, container, false)
        supplier?.let {
            binding.nameSupplier.text = it.name
        }
        viewModel.listGoods.observe(viewLifecycleOwner){
            adapter!!.objectsList = it
        }
        viewModel.findGood.observe(viewLifecycleOwner){
            it?.let { navigator().goToNext(GoodFragment.getInstance(supplier!!, it)) }
        }
        createAdapter()
        viewModel.fillList(supplier!!.code_supplier)
        return binding.root
    }

    private fun createAdapter() {
        adapter = GoodAdapter(this@ListGoodsFragment)
        val layoutManager = LinearLayoutManager(requireContext().applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listObjects.layoutManager = layoutManager
        binding.listObjects.adapter = adapter;
    }

    override fun onStart() {
        super.onStart()
        activity?.registerReceiver(myBroadcastReceiver, filter);
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(myBroadcastReceiver);
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun goToCardGood(arg: GoodObjectForList) {
        navigator().goToNext(GoodFragment.getInstance(supplier!!, arg))
    }

    private val myBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == resources.getString(R.string.activity_intent_filter_action)) {
                try {
                    var decodedData =
                        intent.getStringExtra(resources.getString(R.string.datawedge_intent_key_data))
                    viewModel.getGoodByBarCodeAndSupplier(supplier!!.code_supplier, decodedData!!)
                } catch (e: Exception) {
                }
            }

        }
    }
}