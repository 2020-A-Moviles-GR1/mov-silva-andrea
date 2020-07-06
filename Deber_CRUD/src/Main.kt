import java.io.File
import java.time.LocalDate
import kotlin.collections.ArrayList


fun main(args:Array<String>){
   // println("--------------Menu-------------")
   // menu("3")
    //var estudiantes=Alumno()
    //estudiantes.crearEstudiante()
    var aula=Aulas()
    aula.crearAula()

}
fun menu(opc:String){
    when(opc){
        "1" ->{
          println("Creando aulas y alumnos")
        }
        "2" ->{
            println("Buscar aulas y alumnos")
        }
        "3" ->{
            println("modificar aulas y alumnos")
        }
        "4"->{
            println("eliminar aulas y alumnos")
        }
    }
}
 class Alumno() {
     var id_Alumno:Int=0
     var nombre:String=""
     var sexo:Char=' '
     var fechaNacimiento: LocalDate? =null
     var arrayAlumno:ArrayList<String> = arrayListOf()
     //constructor(id_Alumno: Int, nombre: String, fechaNacimiento: LocalDate, sexo: Char) : this(0,"", LocalDate.of(2000, 1, 1),' '


      fun crearEstudiante(): ArrayList<String> {

          println("Ingrese el id del alumno")
         val line0 = readLine()

         if (line0 != null) {

             id_Alumno = line0.toIntOrNull()!!
             println("El id del alumno es:  ${id_Alumno}")
         }
         println("Ingrese el nombre del alumno")
         val line = readLine()

         if (line != null) {
             nombre = line
             println("El nombre del alumno es:  ${nombre}")
         }
         println("Ingrese la fecha de nacimiento del alumno")
         val line2 = readLine()


             fechaNacimiento = LocalDate.parse(line2)
             println("La fecha de nacimiento  del alumno es:  ${fechaNacimiento}")


         println("Ingrese M si es masculino o F si es femenino el sexo  del alumno")
         val line3 = readLine()
         if (line3 != null) {
             sexo= line3.single()
         }
         println("El sexo del alumno es:  ${sexo}")

          if (line0 != null && line != null && line2 != null && line3 != null   ) {
              arrayAlumno.add(line0)
              arrayAlumno.add(line)
              arrayAlumno.add(line2)
              arrayAlumno.add(line3)
          }
            println(arrayAlumno)
         // archivo(arrayAlumno)
        return arrayAlumno
     }

     fun archivo(tex: ArrayList<String>){
    try {
        val openFile= File("Aulas.txt")
        openFile.appendText(tex.toString())
    }catch (ex:Exception){
        println(ex.message)
    }
     }

 }

class Aulas(){
    var id_aula: Int = 0
    var materia: String = ""
    var numAlumnos: Int = 0
    var array: ArrayList<ArrayList<String>> =  ArrayList(numAlumnos)
    var arrayalumnos=Alumno()
    var arrayAula:ArrayList<String> = arrayListOf()


    fun crearAula() {

        println("Ingrese el id del aula")
        val lineaula0 = readLine()

        if (lineaula0 != null) {

            id_aula = lineaula0.toIntOrNull()!!
            println("El id del alumno es:  ${id_aula}")
        }
        println("Ingrese la materia de aula")
        val lineaula = readLine()

        if (lineaula != null) {
            materia = lineaula
            println("El nombre del alumno es:  ${materia}")
        }
        println("Ingrese el numero de alumnos en el  aula")
        val lineaula2 = readLine()

        if (lineaula2 != null) {
            numAlumnos = lineaula2.toIntOrNull()!!
            println("El numero del alumno es:  ${numAlumnos}")
        }

            var alum: Array<ArrayList<String>> = arrayOf(arrayalumnos.crearEstudiante())
        if (lineaula0 != null && lineaula != null && lineaula2 != null   ) {
            arrayAula.add(lineaula0)
            arrayAula.add(lineaula)
            arrayAula.add(lineaula2)

        }
        println(arrayAula)
        archivo(arrayAula,alum)

    }
    fun archivo(tex: ArrayList<String>,tex2:Array<ArrayList<String>> ){
        try {
            val openFile= File("Aulas.txt")
            openFile.appendText(tex.toString())
        }catch (ex:Exception){
            println(ex.message)
        }
    }
}










