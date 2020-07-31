package com.example.cruapp

class Aulas (var materia:String, var numalumnos:String, var salonDisponible: String,var alumnoAsignado:String) {
    override fun toString(): String {
        return "${this.materia},${this.numalumnos},${this.salonDisponible},${this.alumnoAsignado}"
    }

}