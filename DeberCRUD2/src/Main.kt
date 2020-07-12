import controlador.AlumnoControlador
import controlador.AulasControlador
import java.time.LocalDate


fun main(args:Array<String>){


   // if (line != null) {
   var line:String=""
    while (line != "9"){
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
         line = readLine().toString()
        menu(line)
    }

    //}


    //var estudiantes=AlumnoControlador().buscarAlumnos("Andrea")
    //println(estudiantes.toString())
    //var estudiantes2=AlumnoControlador().listaAlumnos(estudiantes)
    //estudiantes.crearEstudiante()
   // var aula= Aulas()
    //aula.crearAula()
    //aula.buscarAula("mate")
   // var estudiantes=AlumnoControlador().eliminarcascada(3)

// var aulas= AulasControlador().modificarAulas("Lenguaje" ,"Naturales","false","12")

}
fun menu(opc: String?){
    var alumno=AlumnoControlador()
    var aula =AulasControlador()
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
            println("Ingrese el id del alumno que desea modificar ->")
            var modificar= readLine()
            println("Ingrese el nuevo nombre del alumno->")
            var newnombre= readLine()
            println("Ingrese la nueva Fecha de nacimiento del estudiante->")
            var newfecha= readLine()
          //  if (modificar != null && newnombre != null && newfecha != null) {
            if (modificar != null) {
                alumno.modificarAlumno(modificar.toInt(),newnombre,newfecha)
            }
           // }
        }
        "4"->{
            println("eliminar aulas y alumnos")

            println("Ingrese el id del alumno que desea eliminar ->")
            var elimina= readLine()
            if (elimina != null) {
                println(alumno.eliminarcascada(elimina.toInt()))
            }
        }
        "5"->{
            println("Creando alumno")
            var id_Aula:Int=0
            var materia:String=""
            var numAlumnos:Int= 0
            var salonDisponible:Boolean= false
            var idEstudiante:Int=0
            println("Ingrese el Id del aula->")
            var line= readLine()
            if (line != null) {
                id_Aula=line.toInt()
            }
            println("Ingrese el nombre de la materia->")
            var line1= readLine()
            if (line1 != null) {
                materia=line1
            }
            println("Ingrese el numero de alumnos en el aula->")
            var line2= readLine()
            if (line2 != null) {


                numAlumnos= line2.toInt()

            }
            println("Ingrese si el salon esta disponible->")
            var line3= readLine()
            if (line3 != null) {
                salonDisponible = line3.toBoolean()
            }
            println("Ingrese el id del estudante que se encuntra en el aula->")
            var line4= readLine()
            if (line4 != null) {
                idEstudiante = line4.toInt()
            }
            aula.crearAula(id_Aula,materia,numAlumnos,salonDisponible,idEstudiante)

        }
        "6"->{
            println("Buscar aulas y alumnos")
            println("Ingrese la materia que quiere buscar->")
            var buscar= readLine()
            if (buscar != null) {
                println(aula.buscarAulas(buscar))
            }

        }
        "7"->{
            println("modificar aulas")
            println("Ingrese el id de la materia que quiere modificar ->")
            var modificar= readLine()
            println("Ingrese el nuevo nombre de la materia->")
            var newnombremateria= readLine()
            println("Ingrese la nueva cantidad de alumnos->")
            var newnumalumnos= readLine()
            println("Ingrese la nueva disponibilidad del salon->")
            var newsalondisponible= readLine()
            //  if (modificar != null && newnombre != null && newfecha != null) {
            if (modificar != null) {
                aula.modificarAula(modificar.toInt(),newnombremateria,newnumalumnos,newsalondisponible)
            }
            // }
        }
        "8"->{
            println("eliminar aulas y alumnos")

            println("Ingrese el id del aula que desea eliminar ->")
            var elimina= readLine()
            if (elimina != null) {
                println(aula.eliminarAula(elimina.toInt()))
            }
        }
        }

    }




















