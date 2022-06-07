package com.example.falconeai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.falconeai.presentation.viewmodels.ScreenViewModel
import com.example.falconeai.ui.theme.FalconeAITheme
import dagger.hilt.android.AndroidEntryPoint

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
                    val token = myviewmodel.tokenValue.value
                    val planets = myviewmodel.planet.value
                    val vehicles = myviewmodel.vehicle.value
                    token.let {
                        Log.i("MainActivity","Token value = ${it}")
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        planets.let {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                itemsIndexed(it.resultList) { _, plt ->
                                    Text(text = plt.name)
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(text = plt.distance.toString())
                                    Divider()
                                }
                            }
                        }

                        Divider(thickness = 4.dp, color = Color.Red)
                        Button(onClick = {
                            val vehicleName = vehicles.resultList.shuffled().take(4).map {
                                it.name
                            }
                            val planetNames = planets.resultList.shuffled().take(4).map {
                                it.name
                            }
                            val zipped = planetNames.zip(vehicleName)
                            Log.i("MainActivity","names = ${vehicleName}")
                            Log.i("MainActivity","Planet name = ${planetNames}")
                            Log.i("MainActivity","Zipped Value = ${zipped}")

                        }) {
                            Text(text = "Click To Post")
                        }
                        vehicles.let {
                            it.resultList.forEach {
                                Log.i("Main","Vehivle Name =${it.name}")
                                Log.i("Main","Vehivle Speed =${it.speed}")
                                Log.i("Main","Vehivle Distance =${it.max_distance}")
                            }
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                itemsIndexed(it.resultList) { _, vhl ->
                                    Text(text = vhl.name)
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(text = vhl.max_distance.toString())
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(text = vhl.total_no.toString())
                                    Spacer(modifier = Modifier.padding(5.dp))
                                    Text(text = vhl.speed.toString())
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