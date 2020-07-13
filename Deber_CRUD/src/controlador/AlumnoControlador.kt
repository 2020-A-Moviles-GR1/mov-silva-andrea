package controlador

import modelo.Alumno
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.LocalDate
import kotlin.collections.ArrayList

class AlumnoControlador {

    fun crearEstudiante(id_Alumno:Int, nombre: String?, sexo:CharArray, fechaNacimiento:LocalDate?){
        var arrayAlumno: java.util.ArrayList<String> = arrayListOf()
        arrayAlumno.add(Alumno(id_Alumno,nombre,sexo,fechaNacimiento).toString())
        escribirarchivo(arrayAlumno)

    }

    fun escribirarchivo(tex: ArrayList<String>){
        try {
            val openFile= File("Alumnos.txt")
            var cambiotext=tex.toString().replace("[","").replace("]","")

            openFile.appendText(cambiotext+"\n")

        }catch (ex:Exception){
            println(ex.message)
        }
    }
    fun leerarchivo(fichero:String): ArrayList<String> {
        var arreglolineas:ArrayList<String> = arrayListOf()
        try {

            val fr = FileReader(fichero)
            val br: BufferedReader =fr.buffered()
            var linea: String=br.use { it.readText()
            }

            arreglolineas=linea.split("\n").toTypedArray().toCollection(ArrayList())
            println("Imprimiendo en leer")

            println(arreglolineas)
            arreglolineas.removeAt(0)
            arreglolineas.removeAt(arreglolineas.size - 1)
            println(arreglolineas)
            fr.close()

        } catch (e: Exception) {
            System.out.println("Excepcion leyendo fichero " + fichero.toString() + ": " + e)
        }
        return arreglolineas
    }
    fun listaAlumnos(listalineas:ArrayList<String>):ArrayList<Alumno>{
        var arraalumnos:ArrayList<Alumno> = arrayListOf()
        listalineas.forEach{
           value-> var arrayDatos: Array<String> =value.split(",").toTypedArray()
            arrayDatos.forEach { ve-> println(ve.toString()) }

            arraalumnos.add(Alumno(
                    arrayDatos[0].toInt(),
                    arrayDatos[1],
                    arrayDatos[2].toCharArray(),
                    LocalDate.parse(arrayDatos[3])

            ))
        }
        //println(arraalumnos)
        return arraalumnos
    }
    fun buscarAlumnos(dat_busco:String): Boolean {
        var lineas=leerarchivo("Alumnos.txt")
        var alumnos=listaAlumnos(lineas)
        var alu1:Boolean=false
        //println(alumnos)
        alumnos.forEach{

            if(it.nombre==dat_busco){
                 alu1=true
            }
        }
       // println(alu1?.nombre)
        return  alu1
    }
    fun modificarAlumno(nombreAlumModificar: String?, newnombre: String?, newfechaNacimiento: String?){
        var lineas=leerarchivo("Alumnos.txt")
        var alumnos=listaAlumnos(lineas)

        alumnos.forEach{

            if(it.nombre.toString()==nombreAlumModificar){

                crearEstudiante(it.id_Alumno,newnombre,it.sexo,LocalDate.parse(newfechaNacimiento))
                //alumnos.remove(it)

            }
        }

    }
}