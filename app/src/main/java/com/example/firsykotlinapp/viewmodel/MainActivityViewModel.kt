package com.example.firsykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firsykotlinapp.MyDatabase
import com.example.firsykotlinapp.model.RecyclerList
import com.example.firsykotlinapp.network.RetrofitInstance
import com.example.firsykotlinapp.network.RetrofitService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {


    val compositeDisposable : CompositeDisposable = CompositeDisposable()
    lateinit var database: MyDatabase

    private val recyclerListLiveData :  MutableLiveData<RecyclerList?> = MutableLiveData()
    val apiError by lazy { MutableLiveData<String>() }
    val apiLoadingProgressBar by lazy { MutableLiveData<Boolean>() }


    fun recyclerListObserver() : MutableLiveData<RecyclerList?>{
        return recyclerListLiveData
    }

    //Coroutine
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




    //RXJava
    fun makeApiCall(page:Int){
        val instance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        apiLoadingProgressBar.postValue(true)
        val single = instance.getDataFromApi("ny",page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onResponse(response)},
                {t ->
                    if(page<=1){
                        roomRX()
                    }
                    else{
                    onFailure(t)}

                }
            )

        compositeDisposable.add(single)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun onResponse(response: RecyclerList) {
        recyclerListLiveData.postValue(response)
    }

    private fun onFailure(t: Throwable) {
        apiError.postValue("cosom amr")
    }

    fun roomRX(){
        database.dao().getItems()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    onResponse(data) }
                , {t -> onFailure(t) }
            )}


//    //CallBack
//    fun callBack(){
//        val instance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
//        val response = instance.getDataFromApi("ny")
//        response.enqueue(object : Callback<RecyclerList> {
//            override fun onResponse(
//                call: Call<RecyclerList?>,
//                response: Response<RecyclerList?>
//            ) {
//            }
//
//            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//            }
//        })
//
//
//
//    }





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




