package com.example.falconeai.Di

import com.example.falconeai.domain.RepoImpl.PlanetRepoImpl
import com.example.falconeai.domain.RepoImpl.TokenRepoImpl
import com.example.falconeai.domain.RepoImpl.VehicleRepoImpl
import com.example.falconeai.domain.use_case.getTokenUseCase
import com.example.falconeai.domain.use_case.planetUseCase
import com.example.falconeai.domain.use_case.vehicleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object UseCaseProviders {
//    @Provides
//    @Singleton
//    fun provideVehicleUseCase(vehicleRepoImpl: VehicleRepoImpl) : vehicleUseCase {
//        return vehicleUseCase(vehicleRepoImpl)
//    }
//    @Provides
//    @Singleton
//    fun providePlanetUseCase(planetRepoImpl: PlanetRepoImpl) : planetUseCase {
//        return planetUseCase(planetRepoImpl)
//    }
//    @Provides
//    @Singleton
//    fun provideTokenUseCase(tokenRepoImpl: TokenRepoImpl) : getTokenUseCase {
//        return getTokenUseCase(tokenRepoImpl)
//    }
//}