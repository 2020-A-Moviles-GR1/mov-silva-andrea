package com.example.moviles

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon
import java.util.*
import kotlin.reflect.KClass

class PokemonHttp (
    var id:Int,
    val createdAt: Long,
    val updatedAt:Long,
    var nombre:String,
    //colocar usuario Any para no causar conflictos
    //puede ser nulo
    //var usuario:Integer,
    var usuario:Any?,
    var batalla:Int?=null
)  {
var fechaCreacion:Date
    var fechaActualiacion:Date
    init {
        fechaCreacion=Date(createdAt)
        fechaActualiacion= Date(updatedAt)
    }

    companion object{
        val myConverter = object: Converter {
            override fun canConvert(cls: Class<*>)
                    = cls == PokemonHttp::class.java

            override fun toJson(value: Any): String
                    = """{"usuario" : "${if ((value as PokemonHttp).usuario == true) 1 else 0}"""

            override fun fromJson(jv: JsonValue): PokemonHttp {
                if (jv.obj?.get("usuario") is Int) {
                    return PokemonHttp(
                        jv.objInt("id"),
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objString("nombre"),
                        jv.objInt("usuario")

                    )
                }else{
                    return PokemonHttp(
                        jv.objInt("id"),
                        jv.objInt("createdAt").toLong(),
                        jv.objInt("updatedAt").toLong(),
                        jv.objString("nombre"),
                        //jv.objInt("usuario"),
                        Klaxon().parseFromJsonObject<UsuarioHttp>(jv.obj?.get("usuario") as JsonObject)


                    )

                }
            }
    }

    }

}