package com.example.firsykotlinapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.firsykotlinapp.model.RecyclerList

@Database(entities = arrayOf(RecyclerList::class), version = 1, exportSchema = false)
@TypeConverters(MyTypeConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dao(): Dao

    companion object{
        private var instance: MyDatabase ? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase ? {
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