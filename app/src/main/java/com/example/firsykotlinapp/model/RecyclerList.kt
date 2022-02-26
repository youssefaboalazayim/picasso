package com.example.firsykotlinapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.firsykotlinapp.MyTypeConverter


@Entity(tableName = "list")
data class RecyclerList(
    @TypeConverters(MyTypeConverter::class)
    val items : ArrayList<RecyclerData>)

data class RecyclerData(
    @PrimaryKey()
    val name : String,
    val description : String,
    val owner : Owner
    )
data class Owner (
    val avatar_url : String
    )
