package com.developmentavk.avk_app_kotlin.presentation.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developmentavk.avk_app_kotlin.domain.LoginUseCase
import com.developmentavk.avk_app_kotlin.domain.NetWorkService
import com.developmentavk.avk_app_kotlin.domain.entity.GetResult
import com.developmentavk.avk_app_kotlin.domain.entity.UserProfile
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.SetLoginUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.SetNameUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.SetPassUseCase
import com.developmentavk.avk_app_kotlin.domain.usecases.userprofile.UserProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class LoginViewModel @Inject constructor(userProfileRepository: UserProfileRepository, netWorkService: NetWorkService) : ViewModel() {


    private val setLoginUseCase: SetLoginUseCase = SetLoginUseCase(userProfileRepository)
    private val setNameUseCase: SetNameUseCase = SetNameUseCase(userProfileRepository)
    private val setPassUseCase: SetPassUseCase = SetPassUseCase(userProfileRepository)
    private val loginUseCase: LoginUseCase = LoginUseCase(netWorkService)


    private var _loginResult = MutableLiveData<Boolean>()
    var loginResult: LiveData<Boolean> = _loginResult
    private val _userProfileLD = MutableLiveData<UserProfile>()
    val userProfileLD: LiveData<UserProfile> = _userProfileLD
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    @Inject
    lateinit var userProfile:UserProfile

    fun login(userlogin: String, password: String) {

        if (isValid(userlogin)) {
            _error.value = "Логин не может быть пустым!"
        } else if (isValid(password)) {
            _error.value = "Пароль не может быть пустым!"
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val response: Response<GetResult> = loginUseCase.login(
                    userlogin,
                    password,
                    userProfile.token,
                    userProfile.release
                )
                if (response.isSuccessful) {

                    val resultIn = response.body()
                    when {
                        resultIn == null -> _error.value = "Не удалось авторизоваться!"
                        resultIn.error.isNotEmpty() -> _error.value = resultIn.error
                        else -> {
                            userProfile.name = resultIn.result["name"].toString()
                            setNameUseCase.setName(userProfile.name)
                            userProfile.login = userlogin
                            setLoginUseCase.setLogin(userProfile.login)
                            userProfile.pass = password
                            setPassUseCase.setPass(userProfile.pass)
                            _loginResult.postValue(true)
                        }
                    }

                } else {

                    _error.postValue("Не удалось авторизоваться!")

                }
            }
        }
    }

    private fun isValid(arg: String): Boolean {
        return arg.isEmpty()
    }

    fun fillLogin() {
        _userProfileLD.value = userProfile
    }

}