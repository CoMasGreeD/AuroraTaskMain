package com.vladshvyrev.auroratask.UI.fragments.FilterFragment

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
        repository.getList()
            .enqueue(object : Callback<List<Data>> {
                override fun onFailure(call: Call<List<Data>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<Data>>,
                    response: Response<List<Data>>
                ) {
                    characterListLiveData.postValue(response.body())
                }
            }
            )
    }
}