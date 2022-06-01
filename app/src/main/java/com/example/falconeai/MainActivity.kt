package com.example.falconeai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.falconeai.data.models.planets
import com.example.falconeai.presentation.viewmodels.ScreenViewModel
import com.example.falconeai.ui.theme.FalconeAITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val myviewmodel by viewModels<ScreenViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            FalconeAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val planets = myviewmodel.planet.value
                    planets.let {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()) {
                            LazyColumn(
                            modifier = Modifier
                                    .fillMaxSize()){
                                itemsIndexed(it.resultList){ _,index ->
                                    Text(text = index.name)
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(text = index.diatance.toString())
                                    Divider()
                                }
                            }
                        }
                    }



                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FalconeAITheme {
        Greeting("Android")
    }
}