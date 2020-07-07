package com.gritinnovation.tmdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gritinnovation.tmdb.repository.DataRepository

class ViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(dataRepository) as T
    }
}