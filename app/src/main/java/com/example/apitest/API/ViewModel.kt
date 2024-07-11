package com.example.apitest.API

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _games=MutableLiveData<List<GameModelItem>>()

    val games : LiveData<List<GameModelItem>> = _games


    init {
        fetchGames()
    }

    fun fetchGames(){
        viewModelScope.launch {
            try{
                val response=apiService.getAllgames()
                _games.value=response
                Log.e("games",response.toString())
            }catch (e:Exception){
                Log.e("error",e.message.toString())
            }
        }
    }
}