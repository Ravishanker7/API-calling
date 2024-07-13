package com.example.apitest.API.ViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apitest.API.Model.ProductsItem

@Composable
fun ProductScreen(productsViewModel: ProductsViewModel = viewModel()){

    val products by productsViewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.fetechProducts()
    }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(products){
                Box(modifier = Modifier.size(200.dp)){
                    AsyncImage(model = it.images.get(0), contentDescription =null )
                    Text(text = it.title)
                    Text(text = it.price.toString())
                }
            }
        }
    }

}
