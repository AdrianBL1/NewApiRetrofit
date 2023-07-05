package com.adrianbl.newapiretrofit.tiempo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adrianbl.newapiretrofit.R
import com.adrianbl.newapiretrofit.databinding.ActivityMainBinding
import com.adrianbl.newapiretrofit.databinding.ActivityTiempoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class TiempoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTiempoBinding
    val URLAPI = "https://samples.openweathermap.org/"

    private val tiempoList: ArrayList<Tiempo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiempoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tiempoActivityRV.layoutManager = LinearLayoutManager(this)
        binding.tiempoActivityRV.adapter = null

        val tiempoAdapter = TiempoAdapter(tiempoList)
        binding.tiempoActivityRV.adapter = tiempoAdapter

        var retrofitTiempo = Retrofit.Builder()
            .baseUrl(URLAPI)
            .addConverterFactory(GsonConverterFactory.create()) //permite convertir el json a clase
            .build()

        var apiTiempo = retrofitTiempo.create(ApiTiempo::class.java)
        var callTiempo = apiTiempo.getTiempo()

        // Empieza a bajarse los datos
        callTiempo.enqueue(object :Callback<Metereologia>{
            // Repuesta de toda la cadena JSON
            override fun onResponse(call: Call<Metereologia>, response: Response<Metereologia>){
                for (res in response.body()?.list!!){
                    Log.e("TAG Respuesta Temperatura: ", res.main.temp)
                    Log.e("TAG Respuesta Humedad: ", res.main.humidity)

                    val metereologia = response.body()
                    if (metereologia != null) {
                        tiempoList.addAll(metereologia.list)
                        binding.tiempoActivityRV.adapter?.notifyDataSetChanged()
                    }
                }
            }

            // En caso de que haya ocurrido un error
            override fun onFailure(call: Call<Metereologia>, t: Throwable) {
                Log.e("TAG Fallo: ", t.toString())
            }
        })
    }
}