package com.example.firsykotlinapp

import androidx.room.TypeConverter
import com.example.firsykotlinapp.model.RecyclerData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyTypeConverter {

    @TypeConverter
    fun fromDataToString(json: String?): ArrayList<RecyclerData> {
        json ?: return arrayListOf()
        val type = object : TypeToken<ArrayList<RecyclerData>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromStringToData(subjects: ArrayList<RecyclerData>?): String {
        subjects?:return ""
        val type = object : TypeToken<ArrayList<RecyclerData>>() {}.type
        return Gson().toJson(subjects, type)
    }





//    @TypeConverter
//    fun fromDataToString(data: RecyclerData): String{
//        return Gson().toJson(data)
//    }
//
//    @TypeConverter
//    fun fromStringToData(stringData: String):RecyclerData{
//        return Gson().fromJson(stringData, RecyclerData::class.java)
//    }
}