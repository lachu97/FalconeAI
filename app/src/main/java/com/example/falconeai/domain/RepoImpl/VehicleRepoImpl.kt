package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.vehicles
import com.example.falconeai.data.repository.VehicleRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class VehicleRepoImpl(val client: HttpClient) : VehicleRepository {
    override suspend fun getVehicles(): List<vehicles> {
        return client.get(APIendpoints.VEHICLE_API){
            headers {
                accept(ContentType.Application.Json)
            }
        }

    }
}