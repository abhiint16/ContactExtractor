package com.example.pratilipi.di.builder

import com.example.pratilipi.views.detail.DetailActivity
import com.example.pratilipi.views.detail.di.DetailActivityModule
import com.example.pratilipi.views.contactlist.ContactListActivity
import com.example.pratilipi.views.contactlist.di.ContactListActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderProvider {

    @ContributesAndroidInjector(modules = [ContactListActivityModule::class])
    abstract fun contactListActivity(): ContactListActivity

    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun contactDetailActivity(): DetailActivity
}