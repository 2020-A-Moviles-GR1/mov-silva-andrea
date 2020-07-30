package com.example.cruapp

import android.util.Log
import android.widget.EditText
import java.time.LocalDate

class ServicioBD {
    companion object{
        var listaAlumnos= arrayListOf<Alumno>()
        fun crearAlumno(
            nombre:String,
            sexo:Char,
            fecaNacimiento: String
        ){
            listaAlumnos.add(Alumno(nombre,sexo,fecaNacimiento))
            Log.i("insertar","lista"+ listaAlumnos.toString())
        }
    }
}