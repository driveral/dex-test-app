package com.sample.dextestapp.ui.login

import androidx.lifecycle.*
import com.sample.dextestapp.util.SingleLiveEvent
import com.sample.domain.ErrorEntity
import com.sample.domain.Result
import com.sample.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor
) : ViewModel() {

    private val _loginSuccessFulEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val loginSuccessFulEvent: LiveData<Unit> = _loginSuccessFulEvent

    private val _loginErrorMessage: MutableLiveData<ErrorEntity> = MutableLiveData()
    val loginErrorMessage: LiveData<ErrorEntity> = _loginErrorMessage

    val username: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    private val _canLogin: MediatorLiveData<Boolean> = MediatorLiveData()
    val canLogin: LiveData<Boolean> = _canLogin

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading


    init {
        _canLogin.addSource(username) {
            _canLogin.value = canLogin()
        }
        _canLogin.addSource(password) {
            _canLogin.value = canLogin()
        }
        _canLogin.addSource(loading) {
            _canLogin.value = canLogin()
        }
    }

    private fun canLogin(): Boolean =
        !(loading.value
            ?: false) and !username.value.isNullOrEmpty() and !password.value.isNullOrEmpty()

    fun login(user: String, pass: String) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = loginInteractor.login(user, pass)) {
                is Result.Failure -> _loginErrorMessage.value = result.error
                is Result.Success -> _loginSuccessFulEvent.call()
            }
            _loading.value = false
        }
    }
}