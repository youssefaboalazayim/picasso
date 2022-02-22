package com.example.firsykotlinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firsykotlinapp.R
import com.example.firsykotlinapp.RecyclerData
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> (){

    var itemsList = ArrayList<RecyclerData>()

    fun setUpdateData (itemsList : ArrayList<RecyclerData> ){
        this.itemsList = itemsList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemsList.get(position))
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageTheme = itemView.findViewById<ImageView>(R.id.imageTheme)
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDesc = itemView.findViewById<TextView>(R.id.textViewDesc)

        fun bind(data : RecyclerData){

            textViewTitle.text = data.name
            textViewDesc.text = data.description

            val url = data.owner.avatar_url
            Picasso.get().load(url).into(imageTheme)
        }
    }
}