package com.example.apitest

sealed class NetworkResponse<T>(val data : T?=null, val error_message : String ){
}