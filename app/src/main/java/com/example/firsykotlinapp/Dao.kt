package com.example.firsykotlinapp

import androidx.room.Dao
import androidx.room.Query
import com.example.firsykotlinapp.model.RecyclerList
import io.reactivex.rxjava3.core.Single


@Dao
interface Dao {
    @Query("select * from list")
    fun getItems(): Single<RecyclerList>

}