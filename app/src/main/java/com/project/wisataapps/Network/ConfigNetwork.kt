package com.project.wisataapps.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    companion object {
        fun getRetrofit() : ApiService{
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://udacoding.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(ApiService::class.java)

            return service
        }
    }
}