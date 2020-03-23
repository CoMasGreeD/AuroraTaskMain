package com.vladshvyrev.auroratask.Repository

import com.vladshvyrev.auroratask.Repository.network.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("CoMasGreeD/AuroraTask/characters")
    fun getList(): Call<List<Data>>


    @GET("CoMasGreeD/AuroraTask/characters/{userId}")
    fun getUserId(
        @Path("userId") id: String?
    ): Call<Data>
}