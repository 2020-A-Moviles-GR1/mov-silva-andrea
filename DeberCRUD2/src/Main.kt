import controlador.AlumnoControlador
import controlador.AulasControlador
import java.time.LocalDate
import javax.swing.JOptionPane


fun main(args:Array<String>){


   // if (line != null) {
  /* var line:String=""
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
*/

    //var estudiantes=AlumnoControlador().buscarAlumnos("Andrea")
    //println(estudiantes.toString())
    //var estudiantes2=AlumnoControlador().listaAlumnos(estudiantes)
    //estudiantes.crearEstudiante()
   // var aula= Aulas()
    //aula.crearAula()
    //aula.buscarAula("mate")
   // var estudiantes=AlumnoControlador().eliminarcascada(3)

// var aulas= AulasControlador().modificarAulas("Lenguaje" ,"Naturales","false","12")
    interfaz()

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

            println("c ->")
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
fun interfaz(){
    var alumno=AlumnoControlador()
    var aula =AulasControlador()
    val options = arrayOf("Alumnos", "Aulas")
    val seleccion=JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menu principal", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0])
    println(seleccion)
    if(seleccion == 0){
        val options = arrayOf("Crear", "Buscar","Modificar","Eliminar")
        val select1=JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menu Alumnos", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0])
        val sexo = arrayOf(
                "Femenino","Masculino"
        )
        if(select1==0){
            var id_Alumno:Int=0
            var nombre:String=""
            var sexo1:CharArray= charArrayOf()
            var fechaNacimiento: LocalDate?=null
            val respuesta1 = JOptionPane.showInputDialog(null, "Escriba el ID del estudiante")
            id_Alumno=respuesta1.toInt()
            val respuesta2 = JOptionPane.showInputDialog(null, "Escriba el Nombre del estudiante")
            nombre=respuesta2
            val respuesta3 = JOptionPane.showInputDialog(null, "Seleccione el sexo del estudiante", "Sexo", JOptionPane.DEFAULT_OPTION,null, sexo,sexo.get(0)) as String
            sexo1=respuesta3.toCharArray()
            val respuesta4 = JOptionPane.showInputDialog(null, "Escriba la fecha de nacimiento con el formato yyyy-MM-dd")
            fechaNacimiento= LocalDate.parse(respuesta4)
            if (alumno.crearEstudiante(id_Alumno,nombre,sexo1,fechaNacimiento)){
                JOptionPane.showMessageDialog(null, "Alumno creado exitosamente");
                interfaz()
            }else{
                JOptionPane.showMessageDialog(null, "El alumno no pudo ser creado", "Error!!", JOptionPane.ERROR_MESSAGE);
                interfaz()
            }

        }else{
            if (select1==1){
                val respuestabus = JOptionPane.showInputDialog(null, "Escriba el Nombre del estudiante")
               var buscar=respuestabus
                if(alumno.buscarAlumnos(buscar)){
                    JOptionPane.showMessageDialog(null, "Alumno encontrado"+buscar);
                    interfaz()
                }else{
                    JOptionPane.showMessageDialog(null, "El alumno no exite en el archivo", "Error!!", JOptionPane.ERROR_MESSAGE);
                    interfaz()
                }
            }else{
                if (select1 == 2){
                    val respuesta1 = JOptionPane.showInputDialog(null, "Ingrese el id del estudiante que quiere modificar")
                    var modifica =respuesta1.toInt()
                    val respuesta2 = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del alumno")
                    var newnombre=respuesta2
                    val respuesta3 = JOptionPane.showInputDialog(null, "Ingrese la nueva Fecha de nacimiento del estudiante con el formato yyyy-MM-dd")
                    var newfecha=respuesta3
                    if (alumno.modificarAlumno(modifica.toInt(),newnombre,newfecha)){
                        JOptionPane.showMessageDialog(null, "Alumno modificado exitosamente");
                        interfaz()
                    }else{
                        JOptionPane.showMessageDialog(null, "El alumno no se pudo modificar", "Error!!", JOptionPane.ERROR_MESSAGE);
                        interfaz()
                    }

                }else{
                    if (select1==3){
                        val elimina = JOptionPane.showInputDialog(null, "Escriba el id Nombre del estudiante que quiere eliminar")
                       if( alumno.eliminarcascada(elimina.toInt())){
                           JOptionPane.showMessageDialog(null, "Alumno eliminado exitosamente");
                           interfaz()
                       }else{
                           JOptionPane.showMessageDialog(null, "El alumno no pudo ser modificado", "Error!!", JOptionPane.ERROR_MESSAGE);
                           interfaz()
                       }

                    }
                }
            }
        }
    }else{
        val options = arrayOf("Crear", "Buscar","Modificar","Eliminar")
        val select2=JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menu Aulas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0])
        if(select2==0){
            var id_Aula:Int=0
            var materia:String=""
            var numAlumnos:Int= 0
            var salonDisponible:Boolean= false
            var idEstudiante:Int=0
            val respuesta1 = JOptionPane.showInputDialog(null, "Ingrese el Id del aula")
            id_Aula=respuesta1.toInt()
            val respuesta2 = JOptionPane.showInputDialog(null, "Ingrese el nombre de la materia")
            materia=respuesta2
            val respuesta3 = JOptionPane.showInputDialog(null, "Ingrese el numero de alumnos en el aula")
            numAlumnos=respuesta3.toInt()
            val respuesta4 = JOptionPane.showInputDialog(null, "Ingrese si el salon esta disponible")
            salonDisponible=respuesta4.toBoolean()
            val respuesta5 = JOptionPane.showInputDialog(null, "Ingrese el id del estudante que se encuntra en el aula")
            idEstudiante=respuesta5.toInt()
            if (aula.crearAula(id_Aula,materia,numAlumnos,salonDisponible,idEstudiante)){
                JOptionPane.showMessageDialog(null, "Aula creada exitosamente");
                interfaz()
            }else{
                JOptionPane.showMessageDialog(null, "El aula no pudo ser creado", "Error!!", JOptionPane.ERROR_MESSAGE);
                interfaz()
            }

        }else{
            if (select2==1){
                val respuestabus = JOptionPane.showInputDialog(null, "Ingrese la materia que quiere buscar")
                var buscar=respuestabus
                if(aula.buscarAulas(buscar)){
                    JOptionPane.showMessageDialog(null, "Aula encontrada"+buscar);
                    interfaz()
                }else{
                    JOptionPane.showMessageDialog(null, "El aula no exite en el archivo", "Error!!", JOptionPane.ERROR_MESSAGE);
                    interfaz()
                }
            }else{
                if (select2 == 2){
                    val respuesta1 = JOptionPane.showInputDialog(null, "Ingrese el id del alumno que desea modificar")
                    var modifica =respuesta1.toInt()
                    val respuesta2 = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de la materia")
                    var newnombremateria=respuesta2
                    val respuesta3 = JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad de alumnos")
                    var newnumalumnos=respuesta3
                    val respuesta4 = JOptionPane.showInputDialog(null, "Ingrese la nueva disponibilidad del salon")
                    var newsalondisponible=respuesta4
                    if (aula.modificarAula(modifica.toInt(),newnombremateria,newnumalumnos,newsalondisponible)){
                        JOptionPane.showMessageDialog(null, "Aula modificado exitosamente");
                        interfaz()
                    }else{
                        JOptionPane.showMessageDialog(null, "El aula no pudo modificarse", "Error!!", JOptionPane.ERROR_MESSAGE);
                        interfaz()
                    }

                }else{
                    if (select2==3){
                        val elimina = JOptionPane.showInputDialog(null, "Ingrese el id del aula que desea eliminar")
                        if(aula.eliminarAula(elimina.toInt())){
                            JOptionPane.showMessageDialog(null, "Aula eliminada exitosamente");
                            interfaz()
                        }else{
                            JOptionPane.showMessageDialog(null, "El aula  no pudo ser eliminada", "Error!!", JOptionPane.ERROR_MESSAGE);
                            interfaz()
                        }

                    }
                }
            }
        }
    }

}























