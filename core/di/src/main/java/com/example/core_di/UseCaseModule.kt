package com.example.core_di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.MatchRepository
import usecase.GetLiveMatchesUsecase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule
{
    @Provides
    fun provideGetliveMatchesUse(repo:MatchRepository):GetLiveMatchesUsecase
    {
        return GetLiveMatchesUsecase(repo)
    }
}