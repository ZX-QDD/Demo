package com.liulishuo.demo

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface AppService{

    @GET("get_data.json")
    fun getAppData(): Observable<App>

    @GET("get_data.json")
    fun getCall(): Call<List<App>>


}