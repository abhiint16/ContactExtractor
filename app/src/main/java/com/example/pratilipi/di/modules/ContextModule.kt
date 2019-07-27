package com.example.pratilipi.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class ContextModule {
    @Inject
    lateinit var context: Context

    @Provides
    fun providesApplicationContext(application: Application): Context {
        return application
    }
}