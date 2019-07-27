package com.example.pratilipi.datamanager.apihelper

import javax.inject.Inject

class ApiHelperImpl : ApiHelper {
    var apiService: ApiService

    @Inject
    constructor(apiService: ApiService) {
        this.apiService = apiService
    }
}