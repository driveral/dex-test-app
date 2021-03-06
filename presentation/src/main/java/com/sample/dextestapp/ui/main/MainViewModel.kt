package com.sample.dextestapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.sample.domain.Post
import com.sample.interactor.GetPostsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostsInteractor: GetPostsInteractor
) : ViewModel() {

    private val _postList: MutableLiveData<List<Post>> = MutableLiveData()
    val postList: LiveData<List<Post>> = _postList

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _loading.value = true
            when (val result = getPostsInteractor.getPosts()) {
                is Either.Left -> TODO()
                is Either.Right -> _postList.value = result.value
            }
            _loading.value = false
        }
    }
}