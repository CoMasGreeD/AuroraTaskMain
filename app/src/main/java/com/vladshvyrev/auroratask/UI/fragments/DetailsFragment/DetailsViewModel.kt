package com.vladshvyrev.auroratask.UI.fragments.DetailsFragment

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vladshvyrev.auroratask.Repository.RemoteRepository
import com.vladshvyrev.auroratask.Repository.network.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {

    private val repository = RemoteRepository.getInstance()

    val userListLiveData = MutableLiveData<Data>()

    fun getUserId(id:String?) {

        repository.getUserId(id)
            .enqueue(object : Callback<Data> {
                override fun onFailure(call: Call<Data>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<Data>,
                    response: Response<Data>
                ) {
                    userListLiveData.postValue(response.body())
                }
            })

    }
}