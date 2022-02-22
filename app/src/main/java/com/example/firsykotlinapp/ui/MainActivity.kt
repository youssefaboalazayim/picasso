package com.example.firsykotlinapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.firsykotlinapp.R

class MainActivity : AppCompatActivity() {

//    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFragment()







//        recyclerView = findViewById(R.id.rv)
//    val recyclerView = findViewById<RecyclerView>(R.id.rv)

    // getting the recyclerview by its id
//    val recyclerView:RecyclerView = findViewById(R.id.rv)
//    // this creates a vertical layout Manager
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    // ArrayList of class ItemsViewModel
//    val data = ArrayList<ItemsViewModel>()
//
//
//    // This loop will create 20 Views containing
//    // the image with the count of view
//    for (i in 1..50) {
//        data.add(ItemsViewModel(R.drawable.icons8_account_skin_type_4_48, "User " + i))
//    }
//
//
//    // This will pass the ArrayList to our Adapter
//    val adapter = CustomAdapter(data)
//    // Setting the Adapter with the recyclerview
//    recyclerView.adapter = adapter

    }

    private fun setupFragment(){
        val fragment= RecyclerListFragment.newInstance()
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, fragment)
        fragmentTransaction.commit()

    }


}