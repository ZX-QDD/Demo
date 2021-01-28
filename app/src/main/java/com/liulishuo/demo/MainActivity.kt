package com.liulishuo.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var string: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         btn_1.setOnClickListener {

//             val retrofit =Retrofit.Builder()
//                 .baseUrl("")
//                 .addConverterFactory(GsonConverterFactory.create())
//                 .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
//                 .build()

             /**
              * 单纯使用retrofit
              */
             ServiceCreator.create(AppService::class.java)
                 .getCall()
//             retrofit.create(AppService::class.java)
//             .getCall()
                 .enqueue(object : Callback<List<App>> {
                        override fun onFailure(call: Call<List<App>>, t: Throwable) {
                     t.printStackTrace()
                     TODO("Not yet implemented")
                 }

                 override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                     val list = response.body()
                     if (list!=null){
                         for (app in list){
                             Log.d("TAG", "onResponse: ")
                         }
                     }
                 }
             })

             /**
              * retrofit+rxjava
              */
             //通过返回的observable进行--subscribe--Observer
//             val service = retrofit.create(AppService::class.java)
//             val observable: Observable<App> = service.getAppData()
//             observable
             ServiceCreator.create(AppService::class.java)

//             retrofit.create(AppService::class.java)
                 .getAppData()//请求
                 .subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
                 .observeOn(AndroidSchedulers.mainThread())//主线程响应
                 .subscribe(object : Observer<App> {       // 订阅
                     override fun onError(e: Throwable) {

                 }
                 override fun onNext(postInfo: App) {
                     Log.i("http返回：", postInfo.toString() + "")

                 }

                     override fun onComplete() {
                         TODO("Not yet implemented")
                     }

                     override fun onSubscribe(d: Disposable?) {
                         TODO("Not yet implemented")
                     }
                 })




         }
    }

}

