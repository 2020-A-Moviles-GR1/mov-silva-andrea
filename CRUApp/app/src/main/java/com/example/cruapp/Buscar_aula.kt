package com.example.cruapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.example.cruapp.HTTP.AulaHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_buscar_aula.*

class Buscar_aula : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.141:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_aula)
        var lista_aulas: ListView = findViewById(R.id.lv_busca_aula)

        var lista_memoria=obtenerAulas()
        var nomaula: EditText = findViewById(R.id.txt_buscar_aulas)
        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
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

        lista_aulas.setAdapter(adaptador)
        nomaula.addTextChangedListener(mSearchTw)

        btn_mostrar_alumno.setOnClickListener {
            mostrarAlumno()
        }

    }
    fun obtenerAulas(): ArrayList<Aulas> {
        val url = urlGeneral + "/aula"
        var listaAulas= arrayListOf<Aulas>()
        var peticion=url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val aulas = Klaxon().parseArray<AulaHttp>(data)
                    if (aulas != null) {
                        aulas.forEach {
                            Log.i(
                                "http-klaxon", "Materia:${it.materia}"

                            )

                            listaAulas.add(Aulas(it.materia.toString(), it.numAlumnos.toString(),
                                it.salonDisponible.toString(),it.alumnoasignado.toString()))


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
        return listaAulas

    }
    fun mostrarAlumno(){
        val  intentExplicito= Intent(
            this,mostrar_alumnos::class.java
        )
        this.startActivity(intentExplicito)
    }
}