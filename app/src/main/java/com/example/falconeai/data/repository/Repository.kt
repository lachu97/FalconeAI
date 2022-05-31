package com.example.falconeai.data.repository

import com.example.falconeai.data.models.*

interface PlanetRepository {
    suspend fun getPlanets() : List<planets>
}
interface VehicleRepository {
    suspend fun getVehicles() : List<vehicles>
}
interface TokenRepository{
    suspend fun getToken() : auth
}
interface FindFalconAPI {
    suspend fun findFalcon(data : request) : response
}
