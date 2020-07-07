package controlador

import modelo.Alumno
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class AlumnoControlador {

    fun crearEstudiante(id_Alumno:Int,nombre:String,fechaNacimiento:LocalDate?,sexo:CharArray){
        var arrayAlumno: java.util.ArrayList<String> = arrayListOf()
        arrayAlumno.add(Alumno(id_Alumno,nombre,sexo,fechaNacimiento).toString())
        escribirarchivo(arrayAlumno)

    }

    fun escribirarchivo(tex: ArrayList<String>){
        try {
            val openFile= File("Aulas.txt")
            openFile.writeText(tex.toString())

        }catch (ex:Exception){
            println(ex.message)
        }
    }
    fun leerarchivo(fichero:String): ArrayList<String> {
        var arreglolineas:ArrayList<String> = arrayListOf()
        try {

            val fr = FileReader(fichero)
            val br: BufferedReader =fr.buffered()
            var linea: String=br.use { it.readText().replace("]","").replace("[","")
            }

            arreglolineas=linea.split("\n").toTypedArray().toCollection(ArrayList())


            println(arreglolineas)
            //arreglolineas.removeAt(arreglolineas.size - 1)
            //println(arreglolineas)
            fr.close()

        } catch (e: Exception) {
            System.out.println("Excepcion leyendo fichero " + fichero.toString() + ": " + e)
        }
        return arreglolineas
    }
    fun listaAlumnos(listalineas:ArrayList<String>):ArrayList<Alumno>{
        var arraalumnos:ArrayList<Alumno> = arrayListOf()
        listalineas.forEach{
            it-> var arrayDatos: Array<String> =it.split(",").toTypedArray()
            arrayDatos.forEach { it-> print(it) }
            arraalumnos.add(Alumno(
                    arrayDatos[0].toInt(),
                    arrayDatos[1],
                    arrayDatos[2].toCharArray(),
                    LocalDate.parse(arrayDatos[3])

            ))
        }
        println(arraalumnos)
        return arraalumnos
    }
}