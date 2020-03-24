package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data
import java.io.IOException
import java.net.UnknownHostException

class MainPageViewModel : ViewModel() {



    private val repository = RemoteRepository.getInstance()
    val userListLiveData = MutableLiveData<List<Data>>()
    val messageLivaDataError =  MutableLiveData<String>()

    fun getList() {
    repository.getList().subscribe({
        Log.d("DATA", Thread.currentThread().toString())
        userListLiveData.postValue(it)
    }, {
            error ->
            if(error is UnknownHostException) messageLivaDataError.postValue("BadConnection")
    })

}
}