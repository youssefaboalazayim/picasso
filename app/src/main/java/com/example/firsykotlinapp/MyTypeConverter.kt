package com.example.firsykotlinapp

import androidx.room.TypeConverter
import com.example.firsykotlinapp.model.RecyclerData
import com.google.gson.Gson

class MyTypeConverter {
    @TypeConverter
    fun fromDataToString(data: RecyclerData): String{
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromStringToData(stringData: String):RecyclerData{
        return Gson().fromJson(stringData, RecyclerData::class.java)
    }
}