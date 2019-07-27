package com.example.pratilipi.datamanager

import com.example.pratilipi.datamanager.apihelper.ApiHelper
import com.example.pratilipi.datamanager.dbhelper.DBHelper
import com.example.pratilipi.datamanager.prefhelper.PreferenceHelper

interface DataManager : ApiHelper, DBHelper, PreferenceHelper {
}