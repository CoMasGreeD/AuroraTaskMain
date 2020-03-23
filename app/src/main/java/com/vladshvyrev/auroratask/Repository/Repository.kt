package com.vladshvyrev.auroratask.Repository

import com.vladshvyrev.auroratask.Repository.network.Data
import io.reactivex.Single
import retrofit2.Call

interface Repository {
    fun getList(): Single<List<Data>>
    fun getUserId(id: String?):Call<Data>
}