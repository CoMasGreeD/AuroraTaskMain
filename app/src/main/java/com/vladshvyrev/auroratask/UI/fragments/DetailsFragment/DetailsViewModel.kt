package com.vladshvyrev.auroratask.UI.fragments.DetailsFragment

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {

    private val repository = RemoteRepository.getInstance()

    val userLiveData = MutableLiveData<Data>()

    fun getUserId(id:String?) {

        repository.getUserId(id).subscribe({
            Log.d("DATA", Thread.currentThread().toString())
            userLiveData.postValue(it)
        }, { Error ->
            Log.d("DATA", "ERROR  " + Thread.currentThread().toString())
        })



    }
}