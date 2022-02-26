package com.example.firsykotlinapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.firsykotlinapp.model.RecyclerList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface Dao {
    @Query("select * from list")
    fun getPost(): Single<RecyclerList>

    @Insert
    fun insertPost(post: RecyclerList?): Completable?


}