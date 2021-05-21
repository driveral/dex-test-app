package com.sample.dextestapp.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.dextestapp.util.SingleLiveEvent
import com.sample.interactor.IsUserLoggedInInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isUserLoggedInInteractor: com.sample.interactor.IsUserLoggedInInteractor
) : ViewModel() {

    private val _navigateToLoginEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToLoginEvent: LiveData<Unit> = _navigateToLoginEvent

    private val _navigateToContentEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val navigateToContentEvent: LiveData<Unit> = _navigateToContentEvent

    init {
        checkIfUserIsLoggedIn()
    }


    private fun checkIfUserIsLoggedIn() {
        viewModelScope.launch {
            if (isUserLoggedInInteractor.isUserLoggedIn()) {
                _navigateToContentEvent.call()
            } else {
                _navigateToLoginEvent.call()
            }
        }
    }
}