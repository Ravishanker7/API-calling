package com.example.apitest.API

import android.content.ContentValues.TAG
import android.util.Log
import com.example.apitest.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow

class MovieRepogistory() {
    val mutableMovieDataState: MutableStateFlow<NetworkResponse<List<GameModelItem>>> = MutableStateFlow(NetworkResponse.loading())
    suspend fun getAllMovieList(){
        mutableMovieDataState.emit(NetworkResponse.loading())
        val response = RetrofitConnection.getInstance().getAllgames()
        if (response.code()==200){
            Log.d(TAG, "Api response : onSuccess(), ${response.body()}")
            mutableMovieDataState.emit(NetworkResponse.success(response.body()))

        }else{
            mutableMovieDataState.emit(NetworkResponse.error(null,
                response.errorBody().toString()
            ))
            Log.d(TAG, "Api response : onFailure(), ${response.errorBody()}")
        }
    }
}
