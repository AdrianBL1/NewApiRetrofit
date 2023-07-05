package com.adrianbl.newapiretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adrianbl.newapiretrofit.databinding.ActivityMainBinding
import com.adrianbl.newapiretrofit.tiempo.TiempoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityTiempo.setOnClickListener {
            val intent = Intent(this, TiempoActivity::class.java)
            startActivity(intent)
        }
    }
}

/*

Array[]
"List"
    "dt"
    "main"
        "temp"
        "humedity"

 */