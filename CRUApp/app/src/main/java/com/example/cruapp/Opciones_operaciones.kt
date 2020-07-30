package com.example.cruapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_opciones_operaciones.*

class Opciones_operaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones_operaciones)
        btn_crear_alumnos.setOnClickListener({
                boton->ir_crear_alumno()
        })

        btn_buscar_alumnos.setOnClickListener({
                boton->ir_buscar_alumno()
        })
    }


    fun ir_crear_alumno(){
        val intentExplicito= Intent(
            this,Crear_alumno::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun ir_buscar_alumno(){
        val intentExplicito= Intent(
            this,Buscar_alumno::class.java
        )
        this.startActivity(intentExplicito)
    }

}