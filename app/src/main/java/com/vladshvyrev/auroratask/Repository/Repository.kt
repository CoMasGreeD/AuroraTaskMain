package com.vladshvyrev.auroratask.Repository

import com.vladshvyrev.auroratask.Repository.network.Data
import retrofit2.Call

interface Repository {
    fun getList(): Call<List<Data>>
    fun getUserId(id: String?):Call<Data>
}