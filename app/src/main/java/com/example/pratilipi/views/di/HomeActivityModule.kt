package com.example.pratilipi.views.di

import androidx.lifecycle.ViewModelProvider
import com.example.pratilipi.datamanager.DataManager
import com.example.pratilipi.utils.ViewModelProviderFactory
import com.example.pratilipi.views.viewmodel.HomeActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun providesHomeActivityViewModel(dataManager: DataManager): HomeActivityViewModel {
        return HomeActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(homeActivityViewModel: HomeActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(homeActivityViewModel)
    }
}