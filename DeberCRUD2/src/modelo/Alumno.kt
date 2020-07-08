package modelo

import java.time.LocalDate

class Alumno(var id_Alumno:Int,
             var nombre: String?,
             var sexo:CharArray,
             var fechaNacimiento: LocalDate?) {

override fun toString():String{
    return "${id_Alumno},${nombre},${sexo[0]},${fechaNacimiento}"
}



}