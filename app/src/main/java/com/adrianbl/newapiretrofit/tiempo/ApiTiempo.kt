package com.adrianbl.newapiretrofit.tiempo

import retrofit2.Call
import retrofit2.http.GET

interface ApiTiempo {
    // Direccion URL
    @GET("data/2.5/forecast?id=524901&appid=64a51a5709c405e548b43f6db7da4c90")

    abstract fun getTiempo(): Call<Metereologia>
}