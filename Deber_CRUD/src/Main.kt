fun main(args:Array<String>){
    var est= Persona("",0,' ')
    est.crearEstudiante()

}
 open class Persona(var nombre:String?,
                    var  edad:Int?,
                    var sexo: Char?

    ) {
     open fun crearEstudiante() {
         println("Ingrese el nombre del alumno")
         val line = readLine()
         println("El nombre del alumno es:  ${line}")
         nombre = line
         println("Ingrese la edad del alumno")
         val line2 = readLine()
         println("La edad del alumno es:  ${line2}")
         if (line2 != null) {
             edad = line2.toInt()
         }


     }
 }







