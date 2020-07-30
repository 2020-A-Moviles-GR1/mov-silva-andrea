package com.example.cruapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_alumnos.setOnClickListener({
            boton->irOperaciones_alumno()
        })
        btn_aulas.setOnClickListener({
                boton->irOperaciones_aulas()
        })
    }

    fun irOperaciones_alumno(){
        val intentExplicito= Intent(
            this,Opciones_operaciones::class.java
        )
        this.startActivity(intentExplicito)
    }
    fun irOperaciones_aulas(){
        val intentExplicito= Intent(
            this,Opciones_operaciones::class.java
        )
        this.startActivity(intentExplicito)
    }

}