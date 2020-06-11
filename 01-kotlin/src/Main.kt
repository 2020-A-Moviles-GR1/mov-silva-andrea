import java.util.*

fun main(args:Array<String>){
    print("Hola")
       // jav Int edad = 31;
    //primero se define si es mutable o imutables
    // dos tipos de variable en cualquier tipo de lengauje
    //Mutable no pueden ser redclaradas, pude ser reasignada con la palabra clave var
    //usar varibles inmutables, casi nunca usar mutables para un codigo mantenidble en el tiempo
    //uso de varibles descriptivas
    //kotlin no especifica el tipo de dato el ; no es necesario
    //las variables deben ser definidas
    //kotlin tiene Duck Typing
    //sintaxis para identificar el tipo var edadCachorro:INT
    var edadProfesor =31
    var edadCachorro:Int
    edadCachorro=3

    edadProfesor =32
    //Inmutable no pueden ser redclaradas no se puede reasignar

    val numeroCuenta =123456
    //numeroCuenta= 456
    //tipos de varibles
    // todas la varibales que se encuntra en java se pue utilizr en kotlin
    val nombreProfesor:  = "Vicente Adrian"
    val sueldo = 12.20
    val apellidosProfesor= 'a'
    //clase en kotlin
    val fechaNacimiento = Date()


    //comparaciones en java
    if (sueldo ==12.20){

    }else{

    }
    //comparaciones en kotlin
    //when acepta diferente tipo de varibles
    //como usar swich
    //dentro del when los casos o usar else
    when (sueldo){
        12.20 -> println("Sueldo normal")
        -12.20 -> println("Sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }
    val esSueldoMayorAlEstablecido =  if (sueldo == 12.20) true else false
    //XPRESION ? X:Y

    calcularSueldo(14.00,1000.00)
    //parametros nombrados
    calcularSueldo(sueldo=800.00,tasa=16.00)





}
//FUNCIONES
//PALABRA CLAVE FUN
//si la funcion no devuelve nada no se especifica
//parametros esepecificar el tipo de dato
//asignar variables por defecto
//pudo usar paramtros por defecto primero o los requeridos
//y despues por defecto
fun calcularSueldo(
        tasa: Double =12.00 //requerido (valor por defecto)
        ,sueldo: Double, //requerido
        calculoEspecial:Int?=null //puede ser nulo
        ): Double {
    if(calculoEspecial != null){
        return sueldo*tasa*calculoEspecial
    }else{
        return sueldo*tasa //no se puede multiplicar un nulo

    }
    //return sueldo*tasa*calculoEspecial //no se puede multiplicar un nulo
}

// Unit= void que es por defecto
fun imprimirMenasaje() {
    println(" ")
}