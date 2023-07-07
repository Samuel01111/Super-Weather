package com.example.superweather.di

import android.app.Application
import android.content.Context
import com.example.superweather.data.db.di.AppDataBaseModule
import com.example.superweather.data.repository.di.NetworkModule
import com.example.superweather.domain.location.di.LocationProviderModule
import com.example.superweather.domain.location.di.LocationSingletonModule
import com.example.superweather.ui.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataSourceModule::class,
    AppDataBaseModule::class,
    ViewModelBuilderModule::class,
    SubcomponentsModule::class,
    NetworkModule::class,
    LocationSingletonModule::class,
    LocationProviderModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance applicationContext: Application
        ): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubcomponentsModule
