package com.developmentavk.avk_app_kotlin.presentation.suppliers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.GetListAllSuppliersUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.SupplierRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListAllSuppliersViewModel @Inject constructor(private val supplierRepository: SupplierRepository) : ViewModel() {

    private val getListAllSuppliersUseCase = GetListAllSuppliersUseCase(supplierRepository)
    private val _objectsList = MutableLiveData<List<SupplierObjectForList>>()
    val objectsList:LiveData<List<SupplierObjectForList>> = _objectsList

    fun fillList() {
        viewModelScope.launch() {
            _objectsList.postValue(getListAllSuppliersUseCase.getListAllSuppliers())
        }
    }
}

