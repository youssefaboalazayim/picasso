package com.example.firsykotlinapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firsykotlinapp.R
import com.example.firsykotlinapp.RecyclerList
import com.example.firsykotlinapp.adapter.RecyclerViewAdapter
import com.example.firsykotlinapp.viewmodel.MainActivityViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    private lateinit var recyclerViewAdapter : RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    fun initViewModel(view: View){
        val recyclerView =   view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter= recyclerViewAdapter
    }

    fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.recyclerListObserver().observe(this, Observer<RecyclerList> {
            if (it != null){
                recyclerViewAdapter.setUpdateData(it.items)
            }
            else{
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance()= RecyclerListFragment()

































        //        fun newInstance(param1: String, param2: String) =
    //
    //            RecyclerListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}