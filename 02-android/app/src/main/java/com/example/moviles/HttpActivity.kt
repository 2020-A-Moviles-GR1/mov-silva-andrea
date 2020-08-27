package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_http.*

class HttpActivity : AppCompatActivity() {
    val urlPrincipal="http://192.168.1.137:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
        btn_obtener.setOnClickListener{
            obtenerUusuarios()
        }
    }
    fun obtenerUusuarios(){
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

        val pokemonIntancia=Klaxon().parse<PokemonHttp>(pokemonString)
        if(pokemonIntancia != null){
            Log.i("http-klaxon","Nombre:${pokemonIntancia.nombre}")
            Log.i("http-klaxon","FECHA:${pokemonIntancia.fechaCreacion}")

        }

        val url=urlPrincipal +"/usuario"
        url.httpGet().responseString{
            request, response, result ->
            when(result){
                is Result.Success ->{
                    val data= result.get()
                    Log.i("http-klaxon","Data ${data}")


                    val usuarios=Klaxon().parseArray<UsuarioHttp>(data)
                    if(usuarios!= null){
                        usuarios.forEach{
                            Log.i("http-klaxon","Nombre:${it.nombre}"+
                            "Estado civil: ${it.estadoCivil}")
                            if(it.pokemons.size >0){
                                it.pokemons.forEach{
                                    Log.i("http-klaxon","Nombre:${it.nombre}")
                                }
                            }
                        }
                    }
                }
                is Result.Failure->{
                    val ex=result.getException()
                    Log.i("http_klaxon","error:${ex.message}")
                }
            }
        }
    }
}