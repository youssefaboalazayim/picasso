package com.example.firsykotlinapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firsykotlinapp.model.RecyclerList

@Database(entities = arrayOf(RecyclerList::class), version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    // create instance

    // invole interdface
    abstract fun postsDao(): Dao?

    companion object{
        private var instance: MyDatabase ? = null

        @Synchronized
        open fun getInstance(context: Context): MyDatabase ? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java, "posts_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }



}