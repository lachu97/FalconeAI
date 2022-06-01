package com.example.falconeai

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    val tok = produceState(initialValue = "Empty Token"
                        , producer = {
                            myviewmodel.getToken()
                            value = myviewmodel.tokenValue.value
                        }
                    )
                    val token = myviewmodel.tokenValue
                    Log.i("MainActivity","Token value =${tok.value}")
                    Log.i("MainActivity","Token value =${token.value}")
                    Greeting(tok.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Token Value =  $name")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FalconeAITheme {
        Greeting("Android")
    }
}