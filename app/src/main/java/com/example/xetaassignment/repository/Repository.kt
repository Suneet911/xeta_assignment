package com.example.xetaassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.xetaassignment.apiService.ApiService
import com.example.xetaassignment.model.ExerciseX

class Repository(private val apiService : ApiService) {

    private val exerciseLiveDAta= MutableLiveData<ExerciseX>()

    val exercise: LiveData<ExerciseX>
        get() = exerciseLiveDAta

    suspend fun fetchData(){
        val result= apiService.getData()
        if (result.body()!= null){
            exerciseLiveDAta.postValue(result.body())
        }
    }
}