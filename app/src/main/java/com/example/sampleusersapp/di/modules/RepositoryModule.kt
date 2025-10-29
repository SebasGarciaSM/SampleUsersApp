package com.example.sampleusersapp.di.modules

import com.example.sampleusersapp.data.repositories.UserRepositoryImpl
import com.example.sampleusersapp.domain.interfaces.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): IUserRepository

}