package com.project.wisataapps.Network

import com.project.wisataapps.Model.ResponseServer
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/?action=findAll")
    fun getDataWisata(): Call<ResponseServer>
}