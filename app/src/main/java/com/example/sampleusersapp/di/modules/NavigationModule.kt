package com.example.sampleusersapp.di.modules

import com.example.sampleusersapp.data.local.NavigationBus
import com.example.sampleusersapp.domain.interfaces.INavigationEmitter
import com.example.sampleusersapp.domain.interfaces.INavigationReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    val navigationBus = NavigationBus()

    @Provides
    @Singleton
    fun provideNavigationEmitter(): INavigationEmitter = navigationBus

    @Provides
    @Singleton
    fun provideNavigationReceiver(): INavigationReceiver = navigationBus

}