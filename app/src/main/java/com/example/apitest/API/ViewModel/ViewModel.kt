package com.example.apitest.API.ViewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.API.Model.Products
import com.example.apitest.API.Model.ProductsItem
import com.example.apitest.API.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val _products= MutableStateFlow<List<ProductsItem>>(emptyList())
    val products : StateFlow<List<ProductsItem>> = _products

    fun fetechProducts(){
        viewModelScope.launch {
            try {
                val response=RetrofitInstance.api.getAllproducts()
                _products.value=response
                Log.d("MSGG", "fetechProducts: ${response}")
            }catch (e : Exception){
                Log.d("MSG", "fetechProducts: ${e.message}")
            }
        }
    }
}