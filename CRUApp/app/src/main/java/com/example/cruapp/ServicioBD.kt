package com.example.cruapp

import android.util.Log

class ServicioBD {
    companion object{
        var listaAlumnos= arrayListOf<Alumno>()
        var listaAulas= arrayListOf<Aulas>()
        fun crearAlumno(
            nombre:String,
            sexo:String,
            fecaNacimiento: String
        ){
            listaAlumnos.add(Alumno(nombre,sexo,fecaNacimiento))

            Log.i("insertar","lista"+ listaAlumnos.toString())
        }

        fun crearAulas(
            materia:String,
            numalumnos:String,
            salonDisponible: String,
            alumnoasignado:String
        ){
            listaAulas.add(Aulas(materia,numalumnos,salonDisponible,alumnoasignado))
            Log.i("insertar","lista"+ listaAulas.toString())
        }
    }
}