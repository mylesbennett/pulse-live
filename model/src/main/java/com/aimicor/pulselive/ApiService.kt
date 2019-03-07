package com.aimicor.pulselive

import com.aimicor.pulselive.model.ItemDetailContainer
import com.aimicor.pulselive.model.ItemListContainer
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("contentList.json")
    fun getItems(): Single<ItemListContainer>

    @GET("content/{id}.json")
    fun getItem(@Path("id") id: Int): Single<ItemDetailContainer>

    companion object {
        const val dateFormat = "dd/MM/yyyy HH:mm"

        fun create(): ApiService {
             return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat(dateFormat).create()))
                .baseUrl("https://dynamic.pulselive.com/test/native/")
                .build().create(ApiService::class.java)
        }
    }
}