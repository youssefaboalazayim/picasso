package com.example.firsykotlinapp.ui

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
 import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firsykotlinapp.*
import com.example.firsykotlinapp.adapter.RecyclerViewAdapter
import com.example.firsykotlinapp.viewmodel.MainActivityViewModel

class RecyclerListFragment : Fragment() {

    private val pageStart: Int = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var totalPages: Int = 1
    private var currentPage: Int = pageStart

    val database: MyDatabase? = MyDatabase.getInstance(requireContext())
    val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


    private lateinit var recyclerViewAdapter : RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initUi(view)
        initViewModel()
        return view
    }

    fun initUi(view: View){
        val recyclerView =   view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.adapter= RecyclerViewAdapter()
        recyclerView.addItemDecoration(decoration)

        recyclerView.addOnScrollListener(object  : ScrollListener(recyclerView.layoutManager as  LinearLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                viewModel.makeApiCall(currentPage)
            }

            override fun getTotalPageCount(): Int {
                return totalPages
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    private fun initViewModel(){
        viewModel.database = database ?: return
        viewModel.recyclerListObserver().observe(viewLifecycleOwner) {
            it?.let {
                recyclerViewAdapter.setUpdateData(it)
            } ?: Toast.makeText(activity, "Error in getting data", Toast.LENGTH_LONG).show()
        }
        viewModel.apiError.observe(viewLifecycleOwner){ errorMessage ->
            Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
        }
        viewModel.apiLoadingProgressBar.observe(viewLifecycleOwner){
            view?.findViewById<ProgressBar>(R.id.main_progress)?.visibility  = VISIBLE
        }
        viewModel.makeApiCall(currentPage)
    }

    companion object {
        fun newInstance()= RecyclerListFragment()
    }
}