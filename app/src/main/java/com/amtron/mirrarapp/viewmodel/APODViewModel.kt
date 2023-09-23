package com.amtron.mirrarapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amtron.mirrarapp.api.Common.Companion.API_KEY
import com.amtron.mirrarapp.model.APODResponse
import com.amtron.mirrarapp.repository.APODRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class APODViewModel(private val apodRepository: APODRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            apodRepository.getApodData(API_KEY)
        }
    }

    val apoData : LiveData<Response<APODResponse>?>
        get() = apodRepository.apodData

}