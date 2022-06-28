package com.example.falconeai.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.falconeai.commons.Resource
import com.example.falconeai.data.models.planets
import com.example.falconeai.data.models.vehicles
import com.example.falconeai.domain.use_case.findFalconUseCase
import com.example.falconeai.domain.use_case.getTokenUseCase
import com.example.falconeai.domain.use_case.planetUseCase
import com.example.falconeai.domain.use_case.vehicleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    val vehicleUseCase: vehicleUseCase,
    val planetUseCase: planetUseCase,
    val tokenUseCase: getTokenUseCase,
    val findFalconUseCase: findFalconUseCase
) : ViewModel() {
    private val _token = mutableStateOf("")
    val tokenValue: State<String> = _token

    private var _planets = mutableStateOf(planetState())
    val planet: State<planetState> = _planets

    private var _vehicles = mutableStateOf(vehicleState())
    val vehicle: State<vehicleState> = _vehicles

    private var _counter = mutableStateOf(0)
    val counter = _counter.value

    private var _selectedPlanet = mutableStateOf(planets("",0))
    val selectPlanet:State<planets> = _selectedPlanet

    var listPairs = mutableListOf(Pair<String,String>("",""))

    fun assignPlanet(plt: planets) {
        _selectedPlanet.value = plt
        Log.i("View","Planet Distance = ${plt.name} --> ${plt.distance}")
    }
    
    data class planetState(
        var resultList: List<planets> = emptyList(),
        var Error: String? = null,
        var loading: Boolean = false
    )

    data class vehicleState(
        var resultList: List<vehicles> = emptyList(),
        var Error: String? = null,
        var loading: Boolean = false
    )

    fun addPairs(pair:Pair<String,String>){
        listPairs.add(pair)
        listPairs.forEach {
            Log.i("ViewModel","Pair Value in ViewModels->${it.first} ---> ${it.second}")
        }

    }
    init {
        getToken()

        getPlanets()

        getVehicles()

    }

    fun getToken() {
        tokenUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _token.value = "Loading"
                }
                is Resource.Success -> {
                    _token.value = it.data!!.token
                    Log.i("ViewMOdel", "Token Value =${it.data.token}")
                }
                is Resource.Error -> {
                    _token.value = it.message ?: "Error in fetching Token Value"
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getVehicles() {
        vehicleUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _vehicles.value = vehicleState(
                        loading = true
                    )
                }
                is Resource.Success -> {
                    _vehicles.value = vehicleState(
                        resultList = result.data!!
                    )
                }
                is Resource.Error -> {
                    _vehicles.value = vehicleState(
                        Error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPlanets() {

        planetUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _planets.value = planetState(
                        loading = true
                    )
                }
                is Resource.Success -> {
                    _planets.value = planetState(
                        resultList = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _planets.value = planetState(
                        Error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)


    }
}