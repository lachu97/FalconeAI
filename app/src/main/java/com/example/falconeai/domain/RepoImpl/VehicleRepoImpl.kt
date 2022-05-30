package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.vehicles
import com.example.falconeai.data.repos.VehicleRepository
import io.ktor.client.*
import io.ktor.client.request.*

class VehicleRepoImpl(val client: HttpClient) : VehicleRepository {
    override suspend fun getVehicles(): List<vehicles> {
        return client.get(APIendpoints.VEHICLE_API)
    }
}