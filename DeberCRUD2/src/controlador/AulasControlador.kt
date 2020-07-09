package controlador

import modelo.Alumno
import modelo.Aulas
import java.io.*
import java.time.LocalDate


class AulasControlador {
    fun crearAula(id_aula: Int, materia: String?, numAlumnos: Int, salonDisponible: Boolean?, idEstudiante: Int) {
        var arrayAula: java.util.ArrayList<String> = arrayListOf()
        var alumprueba = AlumnoControlador()
        var existe = alumprueba.buscaridAlum2(idEstudiante)
        println("Imprimiendo id alumno")
        println(idEstudiante)
        println(existe)

        if (existe) {
            arrayAula.add(Aulas(id_aula, materia, numAlumnos, salonDisponible, idEstudiante).toString())
            escribirarchivo(arrayAula)
        } else {
            println("Alumno no existe no se puede crear el aula")
        }


    }

    fun escribirarchivo(tex: ArrayList<String>) {
        try {
            val openFile = File("Aulas.txt")
            var cambiotext = tex.toString().replace("[", "").replace("]", "")

            openFile.appendText(cambiotext + "\n")

        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    fun leerarchivo(fichero: String): ArrayList<String> {
        var arreglolineas: ArrayList<String> = arrayListOf()
        try {

            val fr = FileReader(fichero)
            val br: BufferedReader = fr.buffered()
            var linea: String = br.use {
                it.readText()
            }

            arreglolineas = linea.split("\n").toTypedArray().toCollection(ArrayList())
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

    fun listaAulas(listalineas: ArrayList<String>): ArrayList<Aulas> {
        var arraaulas: ArrayList<Aulas> = arrayListOf()
        listalineas.forEach { value ->
            var arrayDatos: Array<String> = value.split(",").toTypedArray()
            arrayDatos.forEach { ve -> println(ve.toString()) }

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

    fun buscarAulas(dat_busco: String): Boolean {
        var lineas = leerarchivo("Aulas.txt")
        var aulas = listaAulas(lineas)
        var alu1: Boolean = false
        //println(alumnos)
        aulas.forEach {
            if (it.materia == dat_busco) {
                alu1 = true
            }
            // println(alu1?.nombre)

        }
        return alu1
    }


    fun listaString(listaAlumnos: ArrayList<Aulas>): ArrayList<String> {
        var arraaulassString: ArrayList<String> = arrayListOf()
        listaAlumnos.forEach {
            arraaulassString.add(it.id_aula.toString())
            arraaulassString.add(it.materia.toString())
            arraaulassString.add(it.numAlumnos.toString())
            arraaulassString.add(it.salonDisponible.toString())
            arraaulassString.add(it.idEstudiante.toString())

        }
        //println(arraalumnos)
        return arraaulassString
    }

    fun buscaridAula(dat_busco:Int): Int {
        var listAlumnos:ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        var elemento:List<Aulas> =listAlumnos.filter {
            return@filter it.id_aula == dat_busco
        }
        var id:Int=listAlumnos.indexOf(elemento[0])
        return id


    }

    fun buscaridAlum2(dat_busco:Int): Boolean{
        var lineas=leerarchivo("Aulas.txt")
        var alumnos=listaAulas(lineas)
        var alu2:Boolean=false
        //println(alumnos)
        alumnos.forEach{

            if(it.id_aula==dat_busco){
                alu2=true
                println("Aula encontrado"+it)
            }
        }
        // println(alu1?.nombre)
        return  alu2


    }
    fun modificarAula(id: Int, newmateria: String?, newnumAlumnos: String?,newSalondisponible:String?){
        var arraAula: ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        arraAula[id].materia=newmateria
        if (newnumAlumnos != null) {
            arraAula[id].numAlumnos= newnumAlumnos.toInt()
        }
        if (newSalondisponible != null) {
            arraAula[id].salonDisponible= newSalondisponible.toBoolean()
        }
        escribir(arraAula,false)


    }


    fun eliminarAula(dat_busco:Int){
        val index:Int=buscaridAula(dat_busco)
        var arraAula:ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        arraAula.removeAt(index)
        escribir(arraAula,false)

    }


    fun escribir(lista: List<Any>, append: Boolean): Unit{
        val archivo: File = File("Aulas.txt")
        val fileOutputStram: FileOutputStream = FileOutputStream(archivo, append)
        fileOutputStram
                .bufferedWriter()
                .use{ out ->
                    lista.forEach{
                        instancia ->
                        out.write(instancia.toString()+ "\n")
                    }
                }


    }
}