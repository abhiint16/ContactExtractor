package com.example.pratilipi.di

import android.app.Application
import com.example.pratilipi.PratilipiApp
import com.example.pratilipi.di.builder.ViewBuilderProvider
import com.example.pratilipi.di.modules.AppModule
import com.example.pratilipi.di.modules.ContextModule
import com.example.pratilipi.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, ContextModule::class, NetworkModule::class,
        AndroidInjectionModule::class, ViewBuilderProvider::class]
)
interface AppComponent {

    fun inject(pratilipiApp: PratilipiApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}