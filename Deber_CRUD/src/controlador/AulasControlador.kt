package controlador

import modelo.Aulas
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class AulasControlador {
    fun crearAula( id_aula:Int,materia:String,numAlumnos:Int,salonDisponible:Boolean,idEstudiante:Int) {
        var arrayAula: java.util.ArrayList<String> = arrayListOf()
        arrayAula.add(Aulas(id_aula,materia,numAlumnos,salonDisponible,idEstudiante).toString())
        escribirarchivo(arrayAula)

    }
    fun escribirarchivo(tex: ArrayList<String>){
        try {
            val openFile= File("Aulas.txt")
            openFile.writeText(tex.toString())

        }catch (ex:Exception){
            println(ex.message)
        }
    }
   fun leerarchivo(fichero:String){
       try {
           val fr = FileReader(fichero)
           val br = BufferedReader(fr)
           var linea: String?
           while (br.readLine().also { linea = it } != null) println(linea)
           fr.close()
       } catch (e: Exception) {
           System.out.println("Excepcion leyendo fichero " + fichero.toString() + ": " + e)
       }
   }
}