package controlador

import modelo.Alumno
import modelo.Aulas
import java.io.BufferedReader
import java.io.File
import java.io.FileReader


class AulasControlador {
    fun crearAula(id_aula:Int, materia: String?, numAlumnos:Int, salonDisponible:Boolean, idEstudiante:Int) {
        var arrayAula: java.util.ArrayList<String> = arrayListOf()
        var alumprueba=AlumnoControlador()
        var existe=alumprueba.buscaridAlum(idEstudiante)
        if(existe){
            arrayAula.add(Aulas(id_aula,materia,numAlumnos,salonDisponible,idEstudiante).toString())
            escribirarchivo(arrayAula)
        }else{
            println("Alumno no existe no se puede crear el aula")
        }


    }
    fun escribirarchivo(tex: ArrayList<String>){
        try {
            val openFile= File("Aulas.txt")
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
            //arreglolineas.removeAt(0)
            arreglolineas.removeAt(arreglolineas.size - 1)
            println(arreglolineas)
            fr.close()

        } catch (e: Exception) {
            System.out.println("Excepcion leyendo fichero " + fichero.toString() + ": " + e)
        }
        return arreglolineas
    }
    fun listaAulas(listalineas:ArrayList<String>):ArrayList<Aulas>{
        var arraaulas:ArrayList<Aulas> = arrayListOf()
        listalineas.forEach{
            value-> var arrayDatos: Array<String> =value.split(",").toTypedArray()
            arrayDatos.forEach { ve-> println(ve.toString()) }

            arraaulas.add(Aulas(
                    arrayDatos[0].toInt(),
                    arrayDatos[1],
                    arrayDatos[2].toInt(),
                    arrayDatos[3].toBoolean(),
                    arrayDatos[4].toInt()

            ))
        }
        //println(arraalumnos)
        return arraaulas
    }
    fun buscarAulas(dat_busco:String): Boolean {
        var lineas = leerarchivo("Aulas.txt")
        var aulas = listaAulas(lineas)
        var alu1: Boolean = false
        //println(alumnos)
        aulas.forEach {
            if ( it.materia== dat_busco){
            alu1 = true
        }
            // println(alu1?.nombre)

        }
        return alu1
    }
    fun modificarAulas(nombremateriaModificar: String?, newnombremateria: String?, newsalondisponible: String?,newnumalumnos:String?){
        var lineas=leerarchivo("Aulas.txt")
        var aulas=listaAulas(lineas)

        aulas.forEach{

            if(it.materia.toString()==nombremateriaModificar){

                //if (newnombremateria != null && newnumalumnos != null  && newsalondisponible != null ) {
                if (newnumalumnos != null && newsalondisponible != null) {
                    crearAula(it.id_aula,newnombremateria,newnumalumnos.toInt(), newsalondisponible.toBoolean(),
                            it.idEstudiante)
                }
                //}
                //alumnos.remove(it)

            }
        }

    }

}