package com.example.moviles

import com.beust.klaxon.TypeFor
import java.util.*
import kotlin.collections.ArrayList

class UsuarioHttp(
    var id:Int,
    var createdAt:Long,
    var updatedAt: Long,
    var cedula: String,
    var nombre:String,
    var correo:String,
    var estadoCivil:String,
    var password:String,
    var pokemons:ArrayList<PokemonHttp>?=null
){
    var fechaCreacion: Date
    var fechaActualiacion: Date
    init {
        fechaCreacion= Date(createdAt)
        fechaActualiacion= Date(updatedAt)
    }

    override fun toString(): String {
        return "Usuario:${id},${createdAt},${updatedAt},${cedula},${nombre},${correo},${estadoCivil}" +
                "${password}"
    }
}

