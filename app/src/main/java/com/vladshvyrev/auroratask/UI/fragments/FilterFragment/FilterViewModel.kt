package com.vladshvyrev.auroratask.UI.fragments.FilterFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterViewModel : ViewModel() {
    private val repository = RemoteRepository.getInstance()
    val characterListLiveData = MutableLiveData<List<Data>>()
    fun getList() {
        repository.getList().subscribe({
            Log.d("DATA", Thread.currentThread().toString())
            characterListLiveData.postValue(it)
        }, { Error ->
            Log.d("DATA", "ERROR  " + Thread.currentThread().toString())
        })

    }
}