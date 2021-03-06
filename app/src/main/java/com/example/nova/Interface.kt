package com.example.nova

import com.example.nova.pojo.Data
import com.example.nova.pojo.DataDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Interface {

    //recomendada
    @GET("products")
    suspend fun fetchDataCoroutines(): Response<List<Data>>

    @GET("products/{id}")
    suspend fun fetchOneData(@Path("id") id: Int): Response<DataDetail>
}