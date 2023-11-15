package com.example.xetaassignment.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtility {

    private const val BASE_URL = "http://52.25.229.242:8000/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}