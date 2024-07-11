package com.example.apitest.API

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apitest.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel (private val movieRepogistory: MovieRepogistory): ViewModel() {

    init {
        callMovieApi()
    }

    fun callMovieApi() {
        viewModelScope.launch {
            movieRepogistory.getAllMovieList()
        }
    }


    val movieResponse : MutableStateFlow<NetworkResponse<List<GameModelItem>>>
        get() = movieRepogistory.mutableMovieDataState
}