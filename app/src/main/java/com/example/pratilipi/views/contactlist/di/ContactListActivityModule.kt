package com.example.pratilipi.views.contactlist.di

import androidx.lifecycle.ViewModelProvider
import com.example.pratilipi.datamanager.DataManager
import com.example.pratilipi.utils.ViewModelProviderFactory
import com.example.pratilipi.views.contactlist.ContactListScopeUtil
import com.example.pratilipi.views.contactlist.viewmodel.ContactListActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class ContactListActivityModule {

    @Provides
    fun providesContactListActivityViewModel(dataManager: DataManager): ContactListActivityViewModel {
        return ContactListActivityViewModel(dataManager)
    }

    @Provides
    fun providesViewModelProvider(contactListActivityViewModel: ContactListActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(contactListActivityViewModel)
    }

    @Provides
    fun providesUtil(): ContactListScopeUtil {
        return ContactListScopeUtil.getInstance()
    }
}