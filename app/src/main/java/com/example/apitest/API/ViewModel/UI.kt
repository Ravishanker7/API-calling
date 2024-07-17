package com.example.apitest.API.ViewModel

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apitest.API.Model.ProductsItem
import okhttp3.internal.wait

@Composable
fun ProductScreen(productsViewModel: ProductsViewModel = viewModel()){

    val products by productsViewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        productsViewModel.fetechProducts()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        ProductPage(product = products)
    }

   /* Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(products){
                ProductPage(product = it)
            /*    Box(modifier = Modifier.size(200.dp)){
                    AsyncImage(model = it.images.get(0), contentDescription =null )
                    Text(text = it.title)
                    Text(text = it.price.toString())
                }*/
            }
        }
    }*/
}


@Composable
fun ProductPage(product: List<ProductsItem>) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Discover",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Notifications, contentDescription = null,
                tint = Color.Black, modifier = Modifier.size(30.dp)
            )
        }
        SeachBar()
        navigationScreens()
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(product){
                Getproductitems(product = it)
            }
        }
    }
}
    @Composable
    fun SeachBar(){
        val productsearch = remember {
            mutableStateOf("")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
        , verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = productsearch.value,
                onValueChange = {
                    productsearch.value=it
                },
                modifier=Modifier.border(BorderStroke(width = 2.dp,Color.Gray)),
                leadingIcon = { Icon(imageVector = Icons.Default.Search,
                    contentDescription =null ) },
                label = { Text(text = "Seach for Clothes") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black,
                    unfocusedLeadingIconColor = Color.Gray,
                    unfocusedTextColor = Color.Gray
                ),
            )

            Box (modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black), contentAlignment = Alignment.Center){
                Icon(imageVector = Icons.Default.List, contentDescription =null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp))
            }
        }
    }

@Composable
fun navigationScreens(){
    val items = listOf(
       "All",
        "Tshirts",
        "Jeans",
        "Shoes",
        "Accessories",
        "Jackets",
    )

    val navSelected = remember {
        mutableStateOf(0)
    }

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(items) {
            if (navSelected.value == items.indexOf(it)) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(40.dp)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navSelected.value = items.indexOf(it)
                        }
                        .border(BorderStroke(width = 1.dp, Color.Gray))
                        , contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.toString(), fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(40.dp)
                        .background(Color.White)
                        .border(color = Color.Gray, width = 1.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable {
                            navSelected.value = items.indexOf(it)
                        }
                        , contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.toString(), fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                // Getitems(text = it.toString())
            }
        }
    }
    /*Row() {
        NavigationBar(modifier = Modifier.background(Color.White)){
            items.forEach {
                NavigationBarItem(selected =navSelected.value==items.indexOf(it),
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White,
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color.Black,
                        unselectedTextColor = Color.Gray,
                    ),
                    onClick = {
                        navSelected.value=items.indexOf(it)
                    },
                    icon = { Text(text = it, fontSize = 16.sp,) },
                    modifier = Modifier.background(Color.White)
                )

            }
        }
    }*/


}
@Composable
fun Getproductitems(product: ProductsItem){
    val image=product.images.lastOrNull()
    Column(modifier = Modifier.fillMaxSize()) {
        if(image!=null){
            AsyncImage(model = image, contentDescription =null )
        }else{
            Text(text = "No Image")
        }
        Text(text = product.title)
        Text(text = product.price.toString())
    }
}

