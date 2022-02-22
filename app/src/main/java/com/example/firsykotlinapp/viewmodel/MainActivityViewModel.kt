package com.example.firsykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firsykotlinapp.RecyclerList
import com.example.firsykotlinapp.network.RetrofitInstance
import com.example.firsykotlinapp.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    lateinit var recyclerListLiveData :  MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun recyclerListObserver() : MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

//    fun makeApiCall(){
//        viewModelScope.launch(Dispatchers.IO){
//            // هنا كده مالينا interface
//            val retroInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
//            //ربط الانشاء الانا عملته ب function الجوا interface
//            val response = retroInstance.getDataFromApi("ny")
//            //  بباصى كلام ده لل mutable
//            recyclerListLiveData.postValue(response)
//        }
//    }

    fun callBack(){
        val instance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val response = instance.getDataFromApi("ny")
        response.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(
                call: Call<RecyclerList?>,
                response: Response<RecyclerList?>
            ) {
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
            }
        })



    }





//    fun getAllMovies() {
//
//        val response = repository.getAllMovies()
//
//        response.enqueue(object : Callback<List<Movie>> {
//            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                movieList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                errorMessage.postValue(t.message)
//            }
//        })
//    }




















//    private void getMovies(String query, int pageNumber){
//        Service.getMoviesApi().searchMovie(Credentials.API_KEY, query, pageNumber)
//            .enqueue(new Callback<MovieSearchResponse>() {
//                @Override
//                public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                    movieMutableLiveData.postValue(response.body().getMovieList());
//                }
//
//                @Override
//                public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//                    Log.v("you", " error ");
//                }
//            });
//    }


}




