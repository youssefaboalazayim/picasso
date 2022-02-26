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

    //RXJava
    fun makeApiCall(page:Int){
        val instance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        apiLoadingProgressBar.postValue(true)
        val single = instance.getDataFromApi("ny",page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                savePost(response)
                onResponse(response)},
                {t ->
                    if(page<=1){
                        getPostFromLocal()
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
        apiError.postValue("Something With Wrong")
    }

    fun getPostFromLocal(){
        database.dao().getPost()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    onResponse(data) }
                , {t -> onFailure(t) }
            )}
    fun savePost(postList : RecyclerList){
        database.dao().insertPost(postList)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({},
                {t ->onFailure(t) }
            )
    }
}




