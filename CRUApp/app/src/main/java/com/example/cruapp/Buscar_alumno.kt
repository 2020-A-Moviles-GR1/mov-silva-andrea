package com.example.cruapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result


class Buscar_alumno : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.13:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_alumno)
        var lista_memoria = obtenerAlumno()

        var lista_alumnos: ListView = findViewById(R.id.lv_buscar)
        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )



        var nomalum: EditText = findViewById(R.id.txt_busca)

        var mSearchTw = object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                adaptador.getFilter().filter(s)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }

        lista_alumnos.setAdapter(adaptador)
        nomalum.addTextChangedListener(mSearchTw)


    }
    fun obtenerAlumno(): ArrayList<Alumno> {
        val url = urlGeneral + "/alumno"
        var listaAlumnos= arrayListOf<Alumno>()
        var peticion=url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val alumnos = Klaxon().parseArray<AlumnoHttp>(data)
                    if (alumnos != null) {
                        alumnos.forEach {
                            Log.i(
                                "http-klaxon", "Nombre:${it.nombre}"

                            )

                           listaAlumnos.add(Alumno(it.nombre.toString(), it.sexo.toString(),it.fecha_nacimiento.toString(),it.latitud.toString(),it.longitud.toString(),it.url.toString()))


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

        }
        peticion.join()
        return listaAlumnos

    }
}
