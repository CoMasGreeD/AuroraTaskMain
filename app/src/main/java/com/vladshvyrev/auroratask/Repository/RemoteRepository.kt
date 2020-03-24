package com.vladshvyrev.auroratask.Repository



import com.vladshvyrev.auroratask.Repository.network.Data
import com.vladshvyrev.auroratask.Repository.network.DataForFilter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RemoteRepository private constructor() : Repository {

    init {
        createApi()
    }
    companion object {
        private var repo: RemoteRepository? = null
        fun getInstance(): RemoteRepository {

            if (repo == null) {
                repo = RemoteRepository()
            }
            return repo!!
        }
    }
    private lateinit var api: ApiInterface



    override fun getList(): Single<List<Data>> = api.getList().subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())

    override fun getUserId(id: String?): Single<Data> = api.getUserId(
        id
    ).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())



//
    private fun createApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build())
            .build()
        api = retrofit.create(ApiInterface::class.java)
//    api.getList()
//        .subscribeOn(Schedulers.io())
//        .unsubscribeOn(Schedulers.computation())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe()
    }
}