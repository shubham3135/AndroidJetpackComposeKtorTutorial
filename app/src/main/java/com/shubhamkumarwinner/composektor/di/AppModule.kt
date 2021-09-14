package com.shubhamkumarwinner.composektor.di

import com.shubhamkumarwinner.composektor.data.domain.repository.PostRepository
import com.shubhamkumarwinner.composektor.data.domain.repository.PostRepositoryImpl
import com.shubhamkumarwinner.composektor.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesApiService(): ApiService = ApiService()

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService): PostRepository = PostRepositoryImpl(apiService)

}