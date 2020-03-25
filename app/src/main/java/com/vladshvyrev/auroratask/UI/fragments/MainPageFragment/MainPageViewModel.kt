package com.vladshvyrev.auroratask.UI.fragments.MainPageFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data
import com.vladshvyrev.auroratask.Repository.network.ExceptionsMessage
import java.net.UnknownHostException

class MainPageViewModel : ViewModel() {



    private val repository = RemoteRepository.getInstance()
    val userListLiveData = MutableLiveData<List<Data>>()
    val ErrorMessagesLivaData =  MutableLiveData<String>()
    private val exceptions = ExceptionsMessage()
    fun getList() {
    repository.getList().subscribe({
        Log.d("DATA", Thread.currentThread().toString())
        userListLiveData.postValue(it)
    }, {
            error ->if(error is  UnknownHostException) ErrorMessagesLivaData.postValue(exceptions.UnknownHostException)

    })

}
}