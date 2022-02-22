package com.example.firsykotlinapp

import java.security.acl.Owner

data class RecyclerList(val items : ArrayList<RecyclerData>)
data class RecyclerData(val name : String, val description : String, val owner : com.example.firsykotlinapp.Owner)
data class Owner (val avatar_url : String)
