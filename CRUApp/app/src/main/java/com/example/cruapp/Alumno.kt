package com.example.cruapp

import android.widget.EditText
import java.time.LocalDate

class Alumno(var nombre:String, var sexo:Char, var fecha_nacimiento: String){
    override fun toString(): String {
        return "${this.nombre},${this.sexo},${this.fecha_nacimiento}"
    }
}