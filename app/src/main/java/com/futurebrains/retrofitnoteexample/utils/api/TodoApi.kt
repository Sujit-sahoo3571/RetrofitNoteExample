package com.futurebrains.retrofitnoteexample.utils.api

import com.futurebrains.retrofitnoteexample.utils.models.Model
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
    suspend fun getTitle() : Response<List<Model>>
}