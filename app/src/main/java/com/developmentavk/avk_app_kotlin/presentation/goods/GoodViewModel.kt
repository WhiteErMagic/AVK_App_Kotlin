package com.developmentavk.avk_app_kotlin.presentation.goods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GoodRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.UpdateDataGoodUseCase
import com.developmentavk.avk_app_kotlin.room.GoodObjectForListTuple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoodViewModel @Inject constructor(private val goodRepository: GoodRepository) : ViewModel() {

    private val updateDataGoodUseCase = UpdateDataGoodUseCase(goodRepository)

    private val _back = MutableLiveData<Boolean>()
    val back:LiveData<Boolean> = _back

    fun updateData(good: GoodObjectForList, qtyFact: Int) {
        val goodObjectForListTuple = GoodObjectForListTuple(good.uid, good.spec, good.supplier, good.pcsFact + qtyFact)
        viewModelScope.launch(Dispatchers.IO) {
            updateDataGoodUseCase.updateDataGood(goodObjectForListTuple)
            _back.postValue(true)
        }
    }

}