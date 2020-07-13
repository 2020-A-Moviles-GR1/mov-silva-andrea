package controlador

import modelo.Alumno
import java.io.*
import java.time.LocalDate


class AlumnoControlador {

    fun crearEstudiante(id_Alumno:Int, nombre: String?, sexo: CharArray?, fechaNacimiento:LocalDate?): Boolean {
        var creado:Boolean=false
        var arrayAlumno: java.util.ArrayList<String> = arrayListOf()
        arrayAlumno.add(Alumno(id_Alumno,nombre,sexo,fechaNacimiento).toString())
        escribirarchivo(arrayAlumno)
        creado=true
        return creado

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
            var linea: String=br.use { it.readText().trim()

            }

            arreglolineas=linea.split("\n").toTypedArray().toCollection(ArrayList())
            println("Imprimiendo en leer")

            println(arreglolineas)
            //arreglolineas.removeAt(0)
            //arreglolineas.removeAt(arreglolineas.size - 1)
            println(arreglolineas)
            fr.close()

        } catch (e: Exception) {
            System.out.println("Excepcion leyendo fichero " + fichero.toString() + ": " + e)
        }
        return arreglolineas
    }
    fun listaAlumnos(listalineas:ArrayList<String>):ArrayList<Alumno>{
        var arraalumnos:ArrayList<Alumno> = arrayListOf()
        listalineas
        listalineas.forEach{
           value-> var arrayDatos: Array<String> =value.split(",").toTypedArray()
           value.trim()
            println("imprimiendo datos sin espacios")
            println(value)


            arraalumnos.add(Alumno(
                    arrayDatos[0].trim().toInt(),
                    arrayDatos[1].trim(),
                    arrayDatos[2].trim().toCharArray(),
                    LocalDate.parse(arrayDatos[3].trim())

            ))
        }
        println("impimiendo array en lectura....")
        println(arraalumnos)
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
                println("Alumno encontrado"+it)
            }
        }
       // println(alu1?.nombre)
        return  alu1
    }
 

    fun buscaridAlum2(dat_busco:Int): Boolean{
        var lineas=leerarchivo("Alumnos.txt")
        var alumnos=listaAlumnos(lineas)
        var alu2:Boolean=false
        //println(alumnos)
        alumnos.forEach{

            if(it.id_Alumno==dat_busco){
                alu2=true
                println("Alumno encontrado"+it)
            }
        }
        // println(alu1?.nombre)
        return  alu2


    }
    fun modificarAlumno(id: Int, newnombre: String?, newfechaNacimiento: String?): Boolean {
        var modifica:Boolean=false
        var arraAlumnos: ArrayList<Alumno> =listaAlumnos(leerarchivo("Alumnos.txt"))
        arraAlumnos.map{

            if(it.id_Alumno==id){
                var indice= arraAlumnos.indexOf(it)
                arraAlumnos[indice].nombre=newnombre
                arraAlumnos[indice].fechaNacimiento= LocalDate.parse(newfechaNacimiento)
                println("imprimiendo dentro del if")
                println(arraAlumnos.toString())

            }
            println("imprimiendo uera del if")
            println(arraAlumnos.toString())

        }
        println("impimiendo fuera de map")
        println(arraAlumnos.toString())
        val bw = BufferedWriter(FileWriter("Alumnos.txt"))
        bw.write("");
        bw.close();
        escribirModifica(arraAlumnos)
        modifica=true

    return modifica

        }


    fun eliminarAlumno(dat_busco:Int){
        var arraAlumnos: ArrayList<Alumno> =listaAlumnos(leerarchivo("Alumnos.txt"))
        println("imprimo array")
        println(arraAlumnos.toString())
        arraAlumnos.removeIf {
            println("Entro al removeif")
             it.id_Alumno==dat_busco         }
        val bw = BufferedWriter(FileWriter("Alumnos.txt"))
        bw.write("");
        bw.close();
        escribirModifica(arraAlumnos)

    }




    fun escribirModifica(alumnomodificado:ArrayList<Alumno>){
       var  arrayAlumno: java.util.ArrayList<String> =arrayListOf()

        println("Impimirnedo en modifica......")
        println(alumnomodificado.toString())
        alumnomodificado.forEach {
            arrayAlumno= arrayListOf(it.id_Alumno.toString(),it.nombre.toString(), it.sexo!![0].toString(),it.fechaNacimiento.toString())
            println("IMPIMIENDO ALUMNO MODIFICADO......")
            println(arrayAlumno.toString())
            escribirarchivoEliminar(arrayAlumno)
        }



    }
    fun escribirarchivoEliminar(tex: java.util.ArrayList<String>){
        try {
            val openFile= File("Alumnos.txt")
            var cambiotext=tex.toString().replace("[","").replace("]","")

            openFile.appendText(cambiotext+"\n")

        }catch (ex:Exception){
            println(ex.message)
        }
    }

    fun eliminarcascada(dat_busco:Int): Boolean {
        var elimino:Boolean=false
        var arraAlumnos: ArrayList<Alumno> =listaAlumnos(leerarchivo("Alumnos.txt"))
        println("imprimo array")
        println(arraAlumnos.toString())

        arraAlumnos.removeIf {
            println("Entro al removeif")
            it.id_Alumno==dat_busco}
        val bw = BufferedWriter(FileWriter("Alumnos.txt"))
        bw.write("");
        bw.close();
        escribirModifica(arraAlumnos)
        var aulaprueba = AulasControlador()
        aulaprueba.eliminarAulacascada(dat_busco)
        elimino=true
        return elimino
    }

    }




