package com.example.apitest.API

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("games")
    suspend fun getAllgames() : Response<List<GameModelItem>>

}