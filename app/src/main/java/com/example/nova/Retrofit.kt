package com.example.nova

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object {
        private const  val URL_BASE = "https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/"

        fun retrofitInstance():Interface{
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Interface::class.java)
        }
    }
}