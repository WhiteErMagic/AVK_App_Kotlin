package com.developmentavk.avk_app_kotlin.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developmentavk.avk_app_kotlin.data.models.GoodDBModel
import com.developmentavk.avk_app_kotlin.data.models.SupplierDBModel
import com.developmentavk.avk_app_kotlin.domain.GetAllDataUseCase
import com.developmentavk.avk_app_kotlin.domain.NetWorkService
import com.developmentavk.avk_app_kotlin.domain.entity.BarCode
import com.developmentavk.avk_app_kotlin.domain.entity.GetResultData
import com.developmentavk.avk_app_kotlin.domain.entity.GetResultDataStruct
import com.developmentavk.avk_app_kotlin.domain.entity.UserProfile
import com.developmentavk.avk_app_kotlin.domain.usecases.barcodes.AddBarCodesUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.barcodes.BarСodesRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.GoodRepository
import com.developmentavk.avk_app_kotlin.domain.usecases.goods.InsertGoodUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.InsertSupplierUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.supliers.SupplierRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


class MainWindowViewModel @Inject constructor(supplierRepository: SupplierRepository,
                                              goodRepository: GoodRepository,
                                              barCodesRepository: BarСodesRepository,
                                              netWorkService: NetWorkService) : ViewModel() {

    private val insertSupplierUseCase = InsertSupplierUseCase(supplierRepository)
    private val insertGoodUseCase = InsertGoodUseCase(goodRepository)
    private val addBarCodesUseCase = AddBarCodesUseCase(barCodesRepository)
    private val getAllDataUseCase = GetAllDataUseCase(netWorkService)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name
    private val _btnLunchTxt = MutableLiveData<Int>()
    val btnLunchTxt: LiveData<Int> = _btnLunchTxt

    @Inject
    lateinit var userProfile: UserProfile

    fun fillData() {
        _name.value = userProfile.name
    }

    fun startExchange() {
        viewModelScope.launch(Dispatchers.IO) {
            val response: Response<GetResultData> = getAllDataUseCase.getAllData(
                userProfile.login,
                userProfile.pass,
                userProfile.token,
                userProfile.release
            )
            if (response.isSuccessful) {
                val resultIn = response.body()
                when {
                    resultIn == null -> _error.value = "Не удалось получить данные!"
                    resultIn.error.isNotEmpty() -> _error.value = resultIn.error
                    else -> {
                            parseAnswer(resultIn.result)
                            _loginResult.postValue(true)
                    }
                }
            } else {

                _error.value = "Не удалось получить данные!"
            }
        }
    }

    private suspend fun parseAnswer(result: GetResultDataStruct?) {

        result?.let {

            result.suppliers.map {
                println(it.toString())
                Gson().fromJson(
                    JSONObject(it).toString(),
                    SupplierDBModel::class.java
                )
                    .let { supplier ->
                        insertSupplierUseCase.insertSupplier(supplier)
                    }
            }


            result.goods.map {
                Gson().fromJson(JSONObject(it).toString(), GoodDBModel::class.java)
                    .let { good ->
                        insertGoodUseCase.insertGood(good)
                    }
            }

            val listBarCode = ArrayList<BarCode>()
            result.barcodes.map {
                Gson().fromJson(JSONObject(it).toString(), BarCode::class.java)
                    .let { good ->
                        listBarCode.add(good)
                    }
            }

            if (listBarCode.size > 0) {
                addBarCodesUseCase.insertBarCodes(listBarCode)
            }

        }
    }

    fun setBtnLunchTxt(lunchTime: Int) {
        _btnLunchTxt.postValue(lunchTime)
    }
}