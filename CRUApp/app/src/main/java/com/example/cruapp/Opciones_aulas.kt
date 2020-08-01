package com.example.cruapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_opciones_aulas.*
import kotlinx.android.synthetic.main.activity_opciones_operaciones.*

class Opciones_aulas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones_aulas)

        btn_crear_aulas.setOnClickListener({
                boton->ir_crear_aulas()
        })

        btn_buscar_aulas.setOnClickListener({
                boton->ir_buscar_aulas()
        })

        btn_actualizar_aulas.setOnClickListener({
                boton->ir_actualizar_aulas()
        })

        btn_borrar.setOnClickListener({
                boton->ir_eliminar_aulas()
        })
    }

    fun ir_crear_aulas(){
        val intentExplicito= Intent(
            this,Crear_aulas::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun ir_buscar_aulas(){
        val intentExplicito= Intent(
            this,Buscar_aula::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun ir_actualizar_aulas(){
        val intentExplicito= Intent(
            this,Actualizar_aula::class.java
        )
        this.startActivity(intentExplicito)
    }

    fun ir_eliminar_aulas(){
        val intentExplicito= Intent(
            this,Eliminar_aulas::class.java
        )
        this.startActivity(intentExplicito)
    }
}