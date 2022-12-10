package com.developmentavk.avk_app_kotlin.presentation.goods

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.developmentavk.avk_app_kotlin.app.TheApplication
import com.developmentavk.avk_app_kotlin.databinding.FragmentGoodBinding
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList
import com.developmentavk.avk_app_kotlin.domain.navigator
import com.developmentavk.avk_app_kotlin.presentation.ViewModelFactory
import javax.inject.Inject

class GoodFragment: Fragment() {

    private var _binding: FragmentGoodBinding? = null
    private val binding get() = _binding!!
    private var supplier:SupplierObjectForList? = null
    private var good:GoodObjectForList? = null

    companion object{
        private const val SUPPLIER = "Supplier"
        private const val GOOD = "Good"
        fun getInstance(supplier: SupplierObjectForList, good: GoodObjectForList): GoodFragment {
            return GoodFragment().apply{
                arguments = Bundle().apply {
                    putParcelable(SUPPLIER, supplier)
                    putParcelable(GOOD, good)}
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[GoodViewModel::class.java]
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
            good = it.getParcelable<GoodObjectForList>(GOOD)
        }
        setFragmentResultListener("addNumber") { _, bundle ->
            val result = bundle.getString("addNumber")
            result?.let { addNumber(it) }
        }
        setFragmentResultListener("removeNumber") { _, _ ->
            removeNumber()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoodBinding.inflate(inflater, container, false)
        viewModel.back.observe(viewLifecycleOwner){
            navigator().goToBack()
        }
        binding.dataGood = good

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener{
            good?.let { it1 -> viewModel.updateData(it1, binding.txtPcs.text.toString().toInt()) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addNumber(arg: String) {
        binding.txtPcs.text = binding.txtPcs.text.toString() + arg
    }

    fun removeNumber() {
        if(binding.txtPcs.text.isNotEmpty())
            binding.txtPcs.text = binding.txtPcs.text.toString().dropLast(1)
    }

}