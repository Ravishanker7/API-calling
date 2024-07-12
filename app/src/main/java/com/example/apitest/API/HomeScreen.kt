package com.example.apitest.API

import android.graphics.Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.apitest.NetworkResponse

val homeViewModel = HomeViewModel(MovieRepogistory())

@Composable
fun HomeScreen() {


    val context = LocalContext.current

    Column() {
        val response = homeViewModel.movieResponse.collectAsState()
        when (response.value) {
            is NetworkResponse.loading -> {


            }
            is NetworkResponse.error -> {

            }
            is NetworkResponse.success -> {
                val data=response.value.data
                if (data != null) {
                    GameScreen(data)
                }
            }
        }
    }
}

@Composable
fun GameScreen(data: List<GameModelItem>) {


    val Display= remember {
        mutableStateOf(false)
    }
    val description= remember {
        mutableStateOf("")
    }
    val titlee= remember {
        mutableStateOf("")
    }
    val thumbnail= remember {
        mutableStateOf("")
    }
    val ReleaseDate= remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Home", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Games", fontSize = 20.sp)
        LazyRow(modifier = Modifier
            .fillMaxWidth()
        ) {
            items(data.size){
                val postData = data.get(it)
                Column(modifier = Modifier
                    .size(200.dp)
                    .clickable {
                        Display.value = true
                        description.value = postData.short_description.toString()
                        titlee.value = postData.title.toString()
                        thumbnail.value = postData.thumbnail.toString()
                        ReleaseDate.value=postData.release_date
                    }
                    .padding(5.dp)){
                    AsyncImage(model = postData.thumbnail, contentDescription ="" ,
                        modifier = Modifier.fillMaxWidth())
                    Text(text = postData.title.toString(), fontSize = 10.sp, color = Color.White)
                }
            }
        }
        DetialScreen(shortDescription = description.value,
            title =titlee.value ,
            thumbnail = thumbnail.value,Display.value,ReleaseDate.value)
        Text(text = "HII", color = Color.White)
    }
        
}

@Composable
fun DetialScreen(shortDescription: String,title: String,thumbnail: String,Display: Boolean,ReleaseDate : String){
    if(Display){

            Column(Modifier.fillMaxWidth()) {
                AsyncImage(model = thumbnail, contentDescription =null, modifier = Modifier.fillMaxWidth() )
                Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp))
                Text(text = "Release Date : ${ReleaseDate}", fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp))
                Text(text = shortDescription, fontWeight = FontWeight.Normal, fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(5.dp))
            }

    }else{
        Box (modifier = Modifier.fillMaxSize()){
            Text(text = " No Data")
        }
    }
}