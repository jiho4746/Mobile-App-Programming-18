package com.example.ch18_network

import android.app.Application
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication:Application() {
    companion object{
        var networkServie : NetworkService
        var networkServiceXml : NetworkService
        val retrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://api.odcloud.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()
        val retrofitxml : Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(TikXmlConverterFactory.create(parser))
                .build()
        init{
            networkServie = retrofit.create(NetworkService::class.java)
            networkServiceXml = retrofitxml.create(NetworkService::class.java)
        }
    }
}