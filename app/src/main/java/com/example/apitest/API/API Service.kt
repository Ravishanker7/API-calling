package com.example.apitest.API

import com.example.apitest.API.Model.ProductsItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService{

  //  https://api.escuelajs.co/api/v1/products
    @GET("products")
    suspend fun getAllproducts() : List<ProductsItem>

}

object RetrofitInstance{
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : APIService by lazy {
        retrofit.create(APIService :: class.java)
    }
}