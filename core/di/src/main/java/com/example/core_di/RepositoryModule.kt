package com.example.core_di

import com.example.data.mapper.repository.MatchRepositoryImpl
import com.example.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.MatchRepository


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
{
    @Provides
    fun providerepository(api: ApiService): MatchRepository
    {
        return MatchRepositoryImpl(api);
    }
}