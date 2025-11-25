package com.example.core_di

import com.example.data.mapper.repository.HomeRespoitoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repository.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule
{

    @Provides
    @Singleton
    fun provideHomeRepository():HomeRepository
    {
        return HomeRespoitoryImpl()
    }
}