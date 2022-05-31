package com.example.falconeai.domain.use_case

import android.accounts.NetworkErrorException
import com.example.falconeai.commons.Resource
import com.example.falconeai.data.models.planets
import com.example.falconeai.domain.RepoImpl.PlanetRepoImpl
import io.ktor.client.call.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpRetryException


class planetUseCase(
    val planetRepoImpl: PlanetRepoImpl
) {
    operator fun invoke(): Flow<Resource<List<planets>>> = flow {
        try {
            emit(Resource.Loading<List<planets>>())
            delay(100L)
            val planets = planetRepoImpl.getPlanets()
            emit(Resource.Success<List<planets>>(data = planets))
        } catch (e: Exception) {
            emit(Resource.Error<List<planets>>(e.message))
        } catch (e: NoTransformationFoundException) {
            emit(Resource.Error<List<planets>>(e.localizedMessage))
        } catch (e: HttpRetryException) {
            emit(Resource.Error<List<planets>>(e.localizedMessage))
        } catch (e: NetworkErrorException) {
            emit(Resource.Error<List<planets>>(e.message))
        }
    }
}