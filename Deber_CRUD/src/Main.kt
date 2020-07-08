import controlador.AlumnoControlador
import java.time.LocalDate


fun main(args:Array<String>){
    println("--------------Menu-------------")
    println("Opciones para seleccionar:")
    println("1) Crear Alumno")
    println("2) Buscar Alumno")
   println("3) Modificar Alumno")
   println("4) Eliminar Alumno")
    println("5) Crear Aula")
    println("6) Buscar Aula")
    println("7) Modificar Aula")
    println("8) Eliminar Aula")
    println("9) SALIR")
   println("Ingrese la opcion que desea--->")
   val line = readLine()

   // if (line != null) {
       menu(line)
    //}


    //var estudiantes=AlumnoControlador().buscarAlumnos("Andrea")
    //println(estudiantes.toString())
    //var estudiantes2=AlumnoControlador().listaAlumnos(estudiantes)
    //estudiantes.crearEstudiante()
   // var aula= Aulas()
    //aula.crearAula()
    //aula.buscarAula("mate")
}
fun menu(opc: String?){
    var alumno=AlumnoControlador()
    when(opc){
        "1" ->{
          println("Creando alumno")
            var id_Alumno:Int=0
            var nombre:String=""
            var sexo:CharArray= charArrayOf()
            var fechaNacimiento: LocalDate?=null
            println("Ingrese el Id del alumno->")
            var line= readLine()
            if (line != null) {
                id_Alumno=line.toInt()
            }
            println("Ingrese el nombre del alumno->")
            var line1= readLine()
            if (line1 != null) {
                nombre=line1
            }
            println("Ingrese el sexo del estudiante F si es femenino o M si es masculino->")
            var line2= readLine()
            if (line2 != null) {


                sexo= line2.toCharArray()

            }
            println("Ingrese la Fecha de nacimiento del estudiante->")
            var line3= readLine()
            if (line3 != null) {
                fechaNacimiento = LocalDate.parse(line3)
            }
             alumno.crearEstudiante(id_Alumno,nombre,sexo,fechaNacimiento)
            //break


        }
        "2" ->{
            println("Buscar aulas y alumnos")
            println("Ingrese el nombre del alumno que busca ->")
            var buscar= readLine()
            if (buscar != null) {
                println(alumno.buscarAlumnos(buscar))
            }

        }
        "3" ->{
            println("modificar aulas y alumnos")
            println("Ingrese el nombre del alumno que desea modificar ->")
            var modificar= readLine()
            println("Ingrese el nuevo nombre del alumno->")
            var newnombre= readLine()
            println("Ingrese la nueva Fecha de nacimiento del estudiante->")
            var newfecha= readLine()
          //  if (modificar != null && newnombre != null && newfecha != null) {
                alumno.modificarAlumno(modificar,newnombre,newfecha)
           // }
        }
        "4"->{
            println("eliminar aulas y alumnos")
        }
    }
}



















