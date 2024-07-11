package com.example.apitest.API

import retrofit2.http.GET

interface ApiService {

    @GET("/games")
    suspend fun getAllgames() : List<GameModelItem>

}