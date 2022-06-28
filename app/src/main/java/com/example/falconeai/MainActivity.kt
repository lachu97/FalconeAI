package com.example.falconeai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.falconeai.cache.TokenStorage
import com.example.falconeai.data.models.vehicles
import com.example.falconeai.presentation.Uiviews.DestinationCard
import com.example.falconeai.presentation.viewmodels.ScreenViewModel
import com.example.falconeai.ui.theme.FalconeAITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val myviewmodel by viewModels<ScreenViewModel>()
        super.onCreate(savedInstanceState)
        val tokenDB = TokenStorage(this)
        val destination = listOf(
            "Destination 1",
            "Destination 2",
            "Destination 3",
            "Destination 4",
        )

        setContent {
            FalconeAITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var tokenvalue by rememberSaveable {
                        mutableStateOf("")
                    }
                    val token = myviewmodel.tokenValue.value
                    val planets = myviewmodel.planet.value
                    val vehicles = myviewmodel.vehicle.value
                    token.let {
                        Log.i("MainActivity", "Token value = ${it}")
                        lifecycleScope.launch {
                            tokenDB.storeToken(it)
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(2.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

//                        LazyColumn( modifier= Modifier.fillMaxWidth().padding(5.dp)){
//                            itemsIndexed(planets.resultList){ _,plt->
//                                DetailCard(title = plt.name,vehicles.resultList,plt.distance)
//                            }
//                        }
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(destination) { dst ->
                                DestinationCard(
                                    title = dst,
                                    planet = planets.resultList,
                                    vehicle = vehicles.resultList,
                                    vm = myviewmodel
                                )
                            }
                        }
//                        AnimatedVisibility(visible = animate) {
//                            LazyColumn {
//                                itemsIndexed(listy) { _, vau ->
//                                    Text(text = "Names =${vau.name}")
//                                }
//                            }
//                        }
                        Button(onClick = {


                            lifecycleScope.launch {
                                tokenDB.getToken.collectLatest { token ->
                                    tokenvalue = token

                                }
                            }
                            Log.i("Main", "Value of Token In DataStore in Savable=${token}")

                        }) {
                            Text(text = "Click To Post")
                        }
//                        vehicles.let {
//                            it.resultList.forEach {
//                                Log.i("Main","Vehivle Name =${it.name}")
//                                Log.i("Main","Vehivle Speed =${it.speed}")
//                                Log.i("Main","Vehivle Distance =${it.max_distance}")
//                            }
//                            LazyColumn(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            ) {
//                                itemsIndexed(it.resultList) { _, vhl ->
//                                    Text(text = vhl.name)
//                                    Spacer(modifier = Modifier.padding(5.dp))
//                                    Text(text = vhl.max_distance.toString())
//                                    Spacer(modifier = Modifier.padding(5.dp))
//                                    Text(text = vhl.total_no.toString())
//                                    Spacer(modifier = Modifier.padding(5.dp))
//                                    Text(text = vhl.speed.toString())
//                                    Divider()
//                                }
//                            }
//                        }


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