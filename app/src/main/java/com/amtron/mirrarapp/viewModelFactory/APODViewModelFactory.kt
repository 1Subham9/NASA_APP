package com.amtron.mirrarapp.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amtron.mirrarapp.repository.APODRepository
import com.amtron.mirrarapp.viewmodel.APODViewModel

class APODViewModelFactory(private val apodRepository: APODRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return APODViewModel(apodRepository)as T
    }
}