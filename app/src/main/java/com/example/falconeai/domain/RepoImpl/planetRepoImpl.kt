package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.planets
import com.example.falconeai.data.repository.PlanetRepository
import io.ktor.client.*
import io.ktor.client.request.*

class PlanetRepoImpl(val client: HttpClient) : PlanetRepository {
    override suspend fun getPlanets(): List<planets> {
        return client.get(APIendpoints.PLANET_API)
    }
}