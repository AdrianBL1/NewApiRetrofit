package com.adrianbl.newapiretrofit.tiempo

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.adrianbl.newapiretrofit.R


class TiempoAdapter (val metereologia: ArrayList<Tiempo>): RecyclerView.Adapter<TiempoAdapter.TiempoViewHolder>() {

    override fun onBindViewHolder(holder: TiempoViewHolder, position: Int) {
        var itemTiempo = metereologia[position]
        holder.bindTiempo(itemTiempo)
    }

    override fun getItemCount(): Int {
        return metereologia.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiempoViewHolder {
        var layoutInflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_tiempo, parent, false)

        return TiempoAdapter.TiempoViewHolder(layoutInflate)
    }

    class TiempoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindTiempo(tiempo: Tiempo){
            var date = Date(tiempo.dt.toLong()*1000)
            var sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var fecha = sdf.format(date)
            Log.d ("TAG TiempoAdapter: ", fecha)

            val listFecha = itemView.findViewById<TextView>(R.id.listFecha)
            listFecha.text = "Fecha: $fecha"

            val listTemperatura = itemView.findViewById<TextView>(R.id.listTemperatura)
            listTemperatura.text = "Temperatura: ${tiempo.main.temp}"

            val listHumedad = itemView.findViewById<TextView>(R.id.listHumedad)
            listHumedad.text = "Humedad: ${tiempo.main.humidity}"
        }
    }
}