package controlador

import modelo.Alumno
import modelo.Aulas
import java.io.*
import java.time.LocalDate


class AulasControlador {
    fun crearAula(id_aula: Int, materia: String?, numAlumnos: Int, salonDisponible: Boolean?, idEstudiante: Int): Boolean {
        var arrayAula: java.util.ArrayList<String> = arrayListOf()
        var alumprueba = AlumnoControlador()
        var existe = alumprueba.buscaridAlum2(idEstudiante)
        var creado:Boolean=false
        println("Imprimiendo id alumno")
        println(idEstudiante)
        println(existe)

        if (existe) {
            arrayAula.add(Aulas(id_aula, materia, numAlumnos, salonDisponible, idEstudiante).toString())
            escribirarchivo(arrayAula)
            creado=true
        } else {
            println("Alumno no existe no se puede crear el aula")
            creado=false
        }
        return creado

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
                it.readText().trim()
            }

            arreglolineas = linea.split("\n").toTypedArray().toCollection(ArrayList())
            println("Imprimiendo en leer")

            println(arreglolineas)
           // arreglolineas.removeAt(0)
           // arreglolineas.removeAt(arreglolineas.size - 1)
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
            value.trim()

            arraaulas.add(Aulas(
                    arrayDatos[0].trim().toInt(),
                    arrayDatos[1].trim(),
                    arrayDatos[2].trim().toInt(),
                    arrayDatos[3].trim().toBoolean(),
                    arrayDatos[4].trim().toInt()

            ))
        }
        //println(arraalumnos)
        return arraaulas
    }

    fun buscarAulas(dat_busco: String): Boolean {
        var lineas = leerarchivo("Aulas.txt")
        var aulas = listaAulas(lineas)
        var aul1: Boolean = false
        //println(alumnos)
        aulas.forEach {
            if (it.materia == dat_busco) {
                aul1 = true
                println("Aula encontrada"+it)
            }
            // println(alu1?.nombre)

        }
        return aul1
    }

  /*  fun buscaridAula(dat_busco:Int): Int {
        var listAlumnos:ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        var elemento:List<Aulas> =listAlumnos.filter {
            return@filter it.id_aula == dat_busco
        }
        var id:Int=listAlumnos.indexOf(elemento[0])
        return id


    }*/
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


    fun modificarAula(id: Int, newmateria: String?, newnumAlumnos: String?,newSalondisponible:String?): Boolean {
        var arraAulas: ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        var modificado:Boolean=false
        arraAulas.map{

            if(it.id_aula==id){
                var indice= arraAulas.indexOf(it)
                arraAulas[indice].materia=newmateria
                arraAulas[indice].numAlumnos= newnumAlumnos!!.toInt()
                arraAulas[indice].salonDisponible= newSalondisponible!!.toBoolean()


                println("imprimiendo dentro del if")
                println(arraAulas.toString())

            }
            println("imprimiendo uera del if")
            println(arraAulas.toString())

        }
        println("impimiendo fuera de map")
        println(arraAulas.toString())
        val bw = BufferedWriter(FileWriter("Aulas.txt"))
        bw.write("");
        bw.close();
        escribirModifica(arraAulas)
        modificado=true

    return modificado

    }




    fun eliminarAula(dat_busco:Int): Boolean {
        var arraAulas: ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
        var elima:Boolean=false
        println("imprimo array")
        println(arraAulas.toString())
        arraAulas.removeIf {
            println("Entro al removeif")
            it.id_aula==dat_busco }
        val bw = BufferedWriter(FileWriter("Aulas.txt"))
        bw.write("");
        bw.close();
        escribirModifica(arraAulas)
        elima=true
        return elima

    }


    fun escribirModifica(aulamodificado:ArrayList<Aulas>){
        var  arrayAulas: java.util.ArrayList<String> =arrayListOf()

        println("Impimirnedo en modifica......")
        println(aulamodificado.toString())
        aulamodificado.forEach {
            arrayAulas= arrayListOf(it.idEstudiante.toString(),it.materia.toString(), it.numAlumnos.toString(),
                    it.salonDisponible.toString(),it.idEstudiante.toString())
            println("IMPIMIENDO ALUMNO MODIFICADO......")
            println(arrayAulas.toString())
            escribirarchivoEliminar(arrayAulas)
        }



    }
    fun escribirarchivoEliminar(tex: java.util.ArrayList<String>){
        try {
            val openFile= File("Aulas.txt")
            var cambiotext=tex.toString().replace("[","").replace("]","")

            openFile.appendText(cambiotext+"\n")

        }catch (ex:Exception){
            println(ex.message)
        }
    }
    fun eliminarAulacascada(idAlum:Int){
        var arraAulas: ArrayList<Aulas> =listaAulas(leerarchivo("Aulas.txt"))
            println("imprimo array")
            println(arraAulas.toString())
            arraAulas.removeIf {
                println("Entro al removeif")
                it.idEstudiante==idAlum}
            val bw = BufferedWriter(FileWriter("Aulas.txt"))
            bw.write("");
            bw.close();
            println("impimiendo aula borrada......")
          println(arraAulas)
            escribirModifica(arraAulas)


    }
}