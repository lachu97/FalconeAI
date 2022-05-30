package com.example.falconeai.data.repos

import com.example.falconeai.data.models.auth
import com.example.falconeai.data.models.planets
import com.example.falconeai.data.models.vehicles

interface PlanetRepository {
    suspend fun getPlanets() : List<planets>
}
interface VehicleRepository {
    suspend fun getVehicles() : List<vehicles>
}
interface TokenRepository{
    suspend fun getToken() : auth
}
