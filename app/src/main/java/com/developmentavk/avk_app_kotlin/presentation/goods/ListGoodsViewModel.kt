package com.developmentavk.avk_app_kotlin.presentation.goods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GetGoodByBarCodeAndSupplierUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GetListGoodsBySupplierUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListGoodsViewModel @Inject constructor(private val goodRepository: GoodRepository) : ViewModel() {

    private val getListGoodsBySupplierUseCase = GetListGoodsBySupplierUseCase(goodRepository)
    private val getGoodByBarCodeAndSupplierUseCase = GetGoodByBarCodeAndSupplierUseCase(goodRepository)

    private val _listGoods = MutableLiveData<List<GoodObjectForList>>()
    val listGoods:LiveData<List<GoodObjectForList>> = _listGoods
    private val _findGood = MutableLiveData<GoodObjectForList>()
    val findGood:LiveData<GoodObjectForList> = _findGood

    fun fillList(codeSupplier: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val dataList = getListGoodsBySupplierUseCase.getListGoodsBySupplier(codeSupplier)
            _listGoods.postValue(dataList)
        }
    }

    fun getGoodByBarCodeAndSupplier(codeSupplier: String, barcode: String){
        viewModelScope.launch(Dispatchers.IO) {
            _findGood.postValue(getGoodByBarCodeAndSupplierUseCase.getGoodByBarCodeAndSupplierUseCase(
                codeSupplier,
                barcode
            ))
        }
    }
}