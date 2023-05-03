package com.example.superweather.di

import android.content.Context
import com.example.superweather.data.db.di.AppDataBaseModule
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
    SubcomponentsModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun mainComponent(): MainComponent.Factory
}

@Module(subcomponents = [MainComponent::class])
object SubcomponentsModule
