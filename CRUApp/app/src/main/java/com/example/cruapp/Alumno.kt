package com.example.cruapp

class Alumno(var nombre:String, var sexo: String, var fecha_nacimiento: String,var latitud:String,
             var longitud:String,var url:String ){
    override fun toString(): String {
        return "${this.nombre},${this.sexo},${this.fecha_nacimiento},${this.latitud}" +
                ",${this.longitud},${this.url}"
    }

}