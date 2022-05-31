package com.example.falconeai.domain.use_case

import android.accounts.NetworkErrorException
import com.example.falconeai.commons.Resource
import com.example.falconeai.data.models.vehicles
import com.example.falconeai.domain.RepoImpl.VehicleRepoImpl
import io.ktor.client.call.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpRetryException

class vehicleUseCase(
    val vehicleRepoImpl: VehicleRepoImpl
){
    operator fun invoke() : Flow<Resource<List<vehicles>>> = flow {
        try {
            emit(Resource.Loading<List<vehicles>>())
            delay(100L)
            val vehicles = vehicleRepoImpl.getVehicles()
            emit(Resource.Success<List<vehicles>>(data = vehicles))
        }catch (e:Exception) {
            emit(Resource.Error<List<vehicles>>(e.localizedMessage))
        } catch (e: NoTransformationFoundException) {
            emit(Resource.Error<List<vehicles>>(e.localizedMessage))
        } catch (e: HttpRetryException) {
            emit(Resource.Error<List<vehicles>>(e.localizedMessage))
        } catch (e: NetworkErrorException) {
            emit(Resource.Error<List<vehicles>>(e.message))
        }

    }
}