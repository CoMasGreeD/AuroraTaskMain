package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data

class MainPageViewModel : ViewModel() {
    private val repository = RemoteRepository.getInstance()
    val userListLiveData = MutableLiveData<List<Data>>()

    fun getList() {
        repository.getList().subscribe({
            Log.d("DATA", Thread.currentThread().toString())
            userListLiveData.postValue(it)
        }, { Error ->
            Log.d("DATA", "ERROR  " + Thread.currentThread().toString())
        })
//            .enqueue(object : Callback<List<Data>> {
//                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
//
//                }
//
//                override fun onResponse(
//                    call: Call<List<Data>>,
//                    response: Response<List<Data>>
//                ) {
//                    userListLiveData.postValue(response.body())
//                }
//            }
//            )
    }


}