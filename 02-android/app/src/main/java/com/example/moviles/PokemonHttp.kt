package com.example.moviles

import java.util.*
import kotlin.reflect.KClass

class PokemonHttp (
    var id:Integer,
    val createdAt: Long,
    val updatedAt:Long,
    var nombre:String,
    var usuario:Integer,
    var batalla:Integer
)  {
var fechaCreacion:Date
    var fechaActualiacion:Date
    init {
        fechaCreacion=Date(createdAt)
        fechaActualiacion= Date(updatedAt)
    }
}