package com.example.pratilipi.views.detail.di

import androidx.lifecycle.ViewModelProvider
import com.example.pratilipi.datamanager.DataManager
import com.example.pratilipi.utils.ViewModelProviderFactory
import com.example.pratilipi.views.detail.DetailUtil
import com.example.pratilipi.views.detail.viewmodel.DetailActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    fun providesDetailActivityViewModel(dataManager: DataManager): DetailActivityViewModel {
        return DetailActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(detailActivityViewModel: DetailActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(detailActivityViewModel)
    }

    @Provides
    fun providesUtil(): DetailUtil {
        return DetailUtil.getInstance()
    }
}