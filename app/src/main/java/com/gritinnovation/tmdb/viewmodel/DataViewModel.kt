package com.gritinnovation.tmdb.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gritinnovation.tmdb.model.DataModel
import com.gritinnovation.tmdb.repository.DataRepository

class DataViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val dataResponse: MutableLiveData<DataModel> = MutableLiveData()

    val responseModel: LiveData<DataModel> = dataResponse

    @SuppressLint("CheckResult")
    fun getDataFromServer(sortBy: String, apiKey: String) {
        dataRepository
            .getDataFromServer(sortBy, apiKey)
            .subscribe {
                dataResponse.postValue(it)
            }
    }
}