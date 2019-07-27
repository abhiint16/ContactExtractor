package com.example.pratilipi.di.builder

import com.example.pratilipi.views.detail.DetailActivity
import com.example.pratilipi.views.detail.di.DetailActivityModule
import com.example.pratilipi.views.home.HomeActivity
import com.example.pratilipi.views.home.di.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun detailActivity(): DetailActivity
}