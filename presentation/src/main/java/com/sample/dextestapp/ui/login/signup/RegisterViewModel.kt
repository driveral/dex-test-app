package com.sample.dextestapp.ui.login.signup

import androidx.lifecycle.*
import arrow.core.Either
import com.sample.dextestapp.util.SingleLiveEvent
import com.sample.domain.ErrorEntity
import com.sample.interactor.RegisterInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerInteractor: RegisterInteractor
) : ViewModel() {

    private val _loginSuccessFulEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val loginSuccessFulEvent: LiveData<Unit> = _loginSuccessFulEvent

    private val _loginErrorMessage: MutableLiveData<ErrorEntity> = MutableLiveData()
    val loginErrorMessage: LiveData<ErrorEntity> = _loginErrorMessage

    val username: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val repeatPassword: MutableLiveData<String> = MutableLiveData("")

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
        _canLogin.addSource(repeatPassword) {
            _canLogin.value = canLogin()
        }
        _canLogin.addSource(loading) {
            _canLogin.value = canLogin()
        }
    }

    private fun canLogin(): Boolean {
        if (loading.value == true) {
            return false
        }
        return !username.value.isNullOrEmpty() and
                !password.value.isNullOrEmpty() and
                !repeatPassword.value.isNullOrEmpty() and
                password.value.equals(repeatPassword.value)
    }

    fun register(user: String, pass: String) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = registerInteractor.register(user, pass)) {
                is Either.Left -> _loginErrorMessage.value = result.value
                is Either.Right -> _loginSuccessFulEvent.call()
            }
            _loading.value = false
        }
    }
}