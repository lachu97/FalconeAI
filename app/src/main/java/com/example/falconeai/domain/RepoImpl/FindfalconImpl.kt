package com.example.falconeai.domain.RepoImpl

import com.example.falconeai.commons.APIendpoints
import com.example.falconeai.data.models.request
import com.example.falconeai.data.models.response
import com.example.falconeai.data.repository.FindFalconAPI
import io.ktor.client.*
import io.ktor.client.request.*

class FindfalconImpl(val client: HttpClient) : FindFalconAPI {
    override suspend fun findFalcon(data: request): response {
        return client.post(APIendpoints.FIND_FALCON_API) {
            body = data
        }
    }
}