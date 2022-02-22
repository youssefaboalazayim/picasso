package com.example.firsykotlinapp.network

import com.example.firsykotlinapp.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getDataFromApi(@Query("q") query : String) : Call<RecyclerList>
//    suspend fun getDataFromApi (@Query("q") query : String) : RecyclerList
}