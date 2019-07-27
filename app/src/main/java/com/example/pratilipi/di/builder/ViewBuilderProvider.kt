package com.example.pratilipi.di.builder

import com.example.pratilipi.views.HomeActivity
import com.example.pratilipi.views.di.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity
}