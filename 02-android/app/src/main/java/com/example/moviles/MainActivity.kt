package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ciclo_vida.setOnClickListener({
            boton -> irCicloVida()
        })
    }
    fun irCicloVida(){
        val intentExplicito=Intent(
            this,ciclovida::class.java
        )
        this.startActivity(intentExplicito)
    }


}