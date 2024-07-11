package com.example.apitest

sealed class NetworkResponse<T>(val data : T?=null, val error_message : String?=null ){
    class loading<T> : NetworkResponse<T> ()
    class success<T>(data: T?) : NetworkResponse<T>(data)
    class error<T>(data : T?, error_message: String?) : NetworkResponse<T>(data,error_message)
}