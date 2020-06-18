import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

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
    //Inmutable no pueden ser redclaradas no se puede reasignar, palabra clave val

    val numeroCuenta =123456
    //numeroCuenta= 456
    //tipos de varibles
    // todas la varibales que se encuentra en java se pue utilizr en kotlin
    val nombreProfesor=   "Vicente Adrian"
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
//array no se puede modificar o añadir mas elementos a diferencia del arraylist
    val arregloConstante: Array<Int> = arrayOf(1,2,3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30,31,22,23,20)
    arregloCumpleanos.add(24)
    print(arregloCumpleanos)
    arregloCumpleanos.remove(30)
    print(arregloCumpleanos)

    //cualquier arreglo tien funciones, para iterar debes de usar el for usaremos  foreach
    //tipos de funciones conocido como operadores de arreglos
    //sintaxis que usa kotlin mediante llaves se ejecuta la parte del codifo y se tien la vaiable it de iteracion de tipo entero
    //otra sintaxis con parentesis o parentesis y llaves
    arregloCumpleanos.forEach{
        println("Valor de la iteración "+ it)
    }
    arregloCumpleanos.forEach(Consumer {
        valorIteracion:Int ->
        println("valor iteracion: "+valorIteracion)
    })
    arregloCumpleanos.forEach{
        valorIteracion:Int ->
        println("valor iteracion: "+valorIteracion)
    }
    val respuestaArregloForEach= arregloCumpleanos.forEachIndexed{
            index:Int, it:Int ->
        println("Valor de la iteración "+ it + index)
    }
    println(respuestaArregloForEach)

    //Opeadores se encuntra disponible en todos los lenguajes
    //ayuda a resolver problemas
    //FOREACH: no devulve nada ->Unit no cambia el arreglo
    // para cambiar los elementos de un arreglo se utiliza el operador MAP
    // map muta el arreglo o lo cambia
    //map uso de return con los valores nuevos
    //1) enviamos el nuevo valor de la iteracion
    //2) nos devulve el NUEVO arreglo con las variables modificadas
    val respuestaMap = arregloCumpleanos.map {iterador: Int ->
         iterador * -1
    }
    println(respuestaMap)
    println(arregloCumpleanos)
    //sintaxis mas de una linea
    val respuestaMapDos = arregloCumpleanos.map {iterador: Int ->
        val nuevoValor =iterador * -1
        val otroValor= nuevoValor*2
        return@map otroValor
    }
    println(respuestaMapDos)

    // Filter -> filtrar el arreglo
    //1) devolver una expresion (true o false)
    //2) nuevo arreglo que cumpla esa expresion
   val respuestaFilter= arregloCumpleanos.filter {iteracion:Int ->
        val esMayor23 =iteracion >23
        return@filter esMayor23
    }
println(respuestaFilter)

    //buscar elementos en el arreglo que cumpla cierta condicion
    //el operador Any ->Filter busca si existe mas de una ocurrencia
    // AND-> TRUE  lo demas false
    //OR-> es falso, lo demas era verdadero
    // all-> AND (EVERY)
    //any -> OR (SOME)
    //1)devolver una expresion true o false
    //2 devuelve un booleano
    val respuesta =arregloCumpleanos.any{
        iterador:Int ->
        return@any iterador <25
    }
    print(respuesta)

    val respuestaAll =arregloCumpleanos.all {
        iterador: Int ->
        return@all iterador > 65
    }
    print (respuestaAll)

    //sacar el promedio de edades
    //reduce
    //1) devulve el acumulado
    //2) en que valor empieza
    //duvuelve un numero
    //tenemos dos variable el acumulado y le iteracion
    //el acumulador empieza en vacio 0
    //el acumulador no simpre hace operacines con numeros
    // si trabajamos con string ("a","b","c","d")
    val respuestarduce= arregloCumpleanos.reduce { acumulado, iteracion ->
        return@reduce acumulado + iteracion

    }
    println(respuestarduce)
    //fold
    //podemos cambiar para que el acumulador inicialice en lo que desemos
val respuestaFold= arregloCumpleanos.fold(100,{
    acumulador, iteracion ->
    return@fold acumulador - iteracion
})
    print(respuestaFold)
//arreglo desde el final
//reduceRigth
    //fold.Right
    //foreach -> nada
    //map -> arreglo
    //filter -> Arreglo
    // all -> booleano
    //any-> booleano
    //reduce-> valor
    //fold-> valor

    // reducir el daño en 20%
    // menores a 18 no hacen daño
    //concatenenar operadores
    val vidaActual= arregloCumpleanos.map { it * 0.8 }
            .filter { it >18 }
            .fold(100.00,{
                acc,iterador -> acc - iterador})
            .also{
                println(it)
            }
    println(vidaActual)














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

    //////////////
    // Clases
    //clase abstracta
    //ayudan a crear otras clases que deriben de ella
    abstract class NumerosJava{
       val numeroUno:Int
       val numeroDos:Int
        constructor(uno:Int,dos:Int){
            numeroUno=uno
            numeroDos=dos
        }
    }
//tenemos variables privadas o protected, public no es necesario ya es por defecto
    abstract class Numeros(val numeroUno:Int,
                           val numeroDos:Int){


    }

    class Suma(
            uno: Int,
            dos: Int):Numeros(uno,dos){
        fun sumar():Int{
            return this.numeroUno + this.numeroDos
        }
    }

    class SumaDos(
            public var uno: Int, //propiedades
            public var dos: Int):Numeros(uno,dos){
        fun sumar():Int{
            this.uno
            this.dos
            return this.numeroUno + this.numeroDos
        }
    }
}