package com.developmentavk.avk_app_kotlin.presentation.suppliers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.developmentavk.avk_app_kotlin.app.TheApplication
import com.developmentavk.avk_app_kotlin.databinding.FragmentListSuppliersBinding
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList
import com.developmentavk.avk_app_kotlin.domain.navigator
import com.developmentavk.avk_app_kotlin.presentation.ViewModelFactory
import com.developmentavk.avk_app_kotlin.presentation.goods.ListGoodsFragment
import javax.inject.Inject

class ListAllSuppliersFragment : Fragment(), SuppliersAdapter.OnClickToSupplier {
    private var _binding: FragmentListSuppliersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SuppliersAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[ListAllSuppliersViewModel::class.java]
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
        _binding = FragmentListSuppliersBinding.inflate(inflater, container, false)

        createAdapter()
        viewModel.objectsList.observe(viewLifecycleOwner, Observer{
            adapter.objectsList = it
        })

        viewModel.fillList()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createAdapter() {
        adapter = SuppliersAdapter(this@ListAllSuppliersFragment)
        val layoutManager = LinearLayoutManager(requireContext().applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listObjects.layoutManager = layoutManager
        binding.listObjects.adapter = adapter
    }

   override fun toSupplier(codeSupplier: SupplierObjectForList) {
       navigator().goToNext(ListGoodsFragment.getInstance(codeSupplier))
   }
}