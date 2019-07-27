package com.example.pratilipi.di.modules

import com.example.pratilipi.AppConstants
import com.example.pratilipi.datamanager.DataManager
import com.example.pratilipi.datamanager.DataManagerImpl
import com.example.pratilipi.datamanager.apihelper.ApiHelper
import com.example.pratilipi.datamanager.apihelper.ApiHelperImpl
import com.example.pratilipi.datamanager.apihelper.ApiService
import com.example.pratilipi.datamanager.dbhelper.DBHelper
import com.example.pratilipi.datamanager.dbhelper.DBHelperImpl
import com.example.pratilipi.datamanager.prefhelper.PreferenceHelper
import com.example.pratilipi.datamanager.prefhelper.PreferenceHelperImpl
import com.example.pratilipi.di.qualifier.PreferenceName
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providesDataManager(dataManagerImpl: DataManagerImpl):
            DataManager {
        return dataManagerImpl
    }

    @Provides
    @Singleton
    fun providesApiHelper(apiHelper: ApiHelperImpl):
            ApiHelper {
        return apiHelper
    }

    @Provides
    fun providesDBHelper(dbHelper: DBHelperImpl):
            DBHelper {
        return dbHelper
    }

    @Provides
    fun providesPrefHelper(preferenceHelper: PreferenceHelperImpl):
            PreferenceHelper {
        return preferenceHelper
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @PreferenceName
    fun providesSharedPrefName(): String {
        return AppConstants.SHARED_PREFERENCE_NAME;
    }
}