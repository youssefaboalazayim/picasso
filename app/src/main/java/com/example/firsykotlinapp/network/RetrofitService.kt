package com.example.firsykotlinapp.network

import com.example.firsykotlinapp.model.RecyclerList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("repositories")
    fun getDataFromApi(@Query("q") query : String,@Query("page")  page:Int) : Single<RecyclerList>
//    fun getDataFromApi(@Query("q") query : String) : Call<RecyclerList>
//    suspend fun getDataFromApi (@Query("q") query : String) : RecyclerList
}