package com.example.xetaassignment.apiService

import com.example.xetaassignment.model.ExerciseX
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/homepage_v2")
    suspend fun getData(): Response<ExerciseX>

}