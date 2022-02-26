package com.example.firsykotlinapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list")
data class RecyclerList(val items : ArrayList<RecyclerData>)

@Entity(tableName = "items")
data class RecyclerData(
    @PrimaryKey()
    val name : String,
    val description : String,
    val owner : Owner
    )
data class Owner (
    val avatar_url : String
    )
