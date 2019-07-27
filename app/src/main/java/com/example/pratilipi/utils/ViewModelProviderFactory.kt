package com.example.pratilipi.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory : ViewModelProvider.Factory {
    var viewModel: ViewModel

    constructor(viewModel: ViewModel) {
        this.viewModel = viewModel
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        } else {
            return throw IllegalArgumentException("Unknown Class Name") as Throwable
        }
    }
}