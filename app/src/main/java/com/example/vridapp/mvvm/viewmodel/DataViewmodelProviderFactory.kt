package com.example.vridapp.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vridapp.mvvm.model.repo.Repository

class DataViewmodelProviderFactory( private val repository: Repository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(repository) as T
    }
}