package com.example.falconeai.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.falconeai.commons.Resource
import com.example.falconeai.domain.use_case.findFalconUseCase
import com.example.falconeai.domain.use_case.getTokenUseCase
import com.example.falconeai.domain.use_case.planetUseCase
import com.example.falconeai.domain.use_case.vehicleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor (
val vehicleUseCase: vehicleUseCase,
val planetUseCase: planetUseCase,
val tokenUseCase : getTokenUseCase,
val findFalconUseCase: findFalconUseCase
) : ViewModel() {
    private val _token = mutableStateOf("")
    val tokenValue : State<String> = _token
    fun getToken() {
        tokenUseCase().onEach {
            when(it) {
                is Resource.Loading ->{
                    _token.value = "Loading"
                }
                is Resource.Success ->{
                    _token.value = it.data!!.token
                    Log.i("ViewMOdel","Token Value =${it.data.token}")
                }
                is Resource.Error ->{
                    _token.value = it.message ?: "Error in fetching Token Value"
                }
            }
        }
    }
}