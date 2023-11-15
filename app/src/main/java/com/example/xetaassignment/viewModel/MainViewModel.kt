package com.example.xetaassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xetaassignment.model.ExerciseX
import com.example.xetaassignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository : Repository) : ViewModel(){

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.fetchData()
        }
    }

    val exercise : LiveData<ExerciseX>
        get() = repository.exercise
}