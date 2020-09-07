package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.beust.klaxon.TypeFor
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*
import kotlin.reflect.typeOf

class HttpActivity : AppCompatActivity() {
    val urlPrincipal = "http://192.168.1.134:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener.setOnClickListener {
            obtenerUusuarios()
        }
        btn_crear.setOnClickListener {
            crearUsuario()
        }
    }

    fun obtenerUusuarios() {
        val pokemonString = """
    {
     "id": 2,
     "createdAt": 1598279793081,
      "updatedAt": 1598279793081,
    "nombre": "Pikachu",
    "usuario": 1,
    "batalla":1
  }
""".trimIndent()

        val pokemonIntancia = Klaxon().parse<PokemonHttp>(pokemonString)
        if (pokemonIntancia != null) {
            Log.i("http-klaxon", "Nombre:${pokemonIntancia.nombre}")
            Log.i("http-klaxon", "FECHA:${pokemonIntancia.fechaCreacion}")

        }

        val url = urlPrincipal + "/usuario"
        url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val usuarios = Klaxon().parseArray<UsuarioHttp>(data)
                    if (usuarios != null) {
                        usuarios.forEach {
                            Log.i(
                                "http-klaxon", "Nombre:${it.nombre}" +
                                        "Estado civil: ${it.estadoCivil}"
                            )
                            /*if(it.pokemons!!.size > 0 ){
                                it.pokemons!!.forEach{
                                    Log.i("http-klaxon","Nombre:${it.nombre}")
                                }
                            }*/

                        }


                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }

            }
            val url2 = urlPrincipal + "/pokemon"
            url2.httpGet().responseString { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        val data2 = result.get()
                        val pokemons = Klaxon().converter(PokemonHttp.myConverter)
                            .parseArray<PokemonHttp>(data2)
                        if (pokemons!!.size != 0) {
                            pokemons!!.forEach {
                                Log.i("http-klaxon", "USUARIO:${it.usuario.toString()}")
                               println(it.usuario?.javaClass?.getSimpleName())


                            }
                        }
                    }
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http_klaxon", "error:${ex.message}")
                    }

                }
            }

        }

    }
    fun crearUsuario() {
        val url = urlPrincipal + "/Usuario"
        val parametrosUsuario: List<Pair<String, String>> = listOf(
            "cedula" to "1920172918",
            "nombre" to "Valeria",
            "correo" to "v.garcia@epn.edu.ec",
            "estadocivil" to "Soltero",
            "password" to "Password8"
        )
        url.httpPost(parametrosUsuario)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "ERROR:${error}")
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                        Log.i("http-klaxon", "${usuarioString}")
                    }
                }
            }

    }
}
