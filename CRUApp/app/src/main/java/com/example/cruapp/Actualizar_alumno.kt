package com.example.cruapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.core.view.forEach
import androidx.core.view.get
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_alumno.*
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*

class Actualizar_alumno : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.141:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_alumno)
        var lista_alumnos: ListView = findViewById(R.id.lv_actualizar)
        var lista_memoria = obtenerAlumno()

        txt_fecha_actualiza.setOnClickListener {
            showDatePickerDialog()
        }


        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        lista_alumnos.setAdapter(adaptador)

        lista_alumnos.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("lis-view", "position$position")
                var alumnoselect = lista_memoria[position]
                var nombre_selct = alumnoselect.nombre
                txt_nombre_actualiza.setText(nombre_selct)
                var sexo_select = alumnoselect.sexo
                btn_sexFact.setEnabled(false);
                btn_sexMact.setEnabled(false);
                if (sexo_select == "F") {
                    btn_sexFact.setChecked(true)

                }

                if (sexo_select == "M") {
                    btn_sexMact.setChecked(true)

                }
                var fecha_select = alumnoselect.fecha_nacimiento
                txt_fecha_actualiza.setText(fecha_select)

                btn_actualizar.setOnClickListener {

                    if (sexo_select == "F") {
                        var newnombre = txt_nombre_actualiza.text.toString()
                        var newsex = "F"
                        var newfecha = txt_fecha_actualiza.text.toString()
                        alumnoselect.nombre = newnombre
                        alumnoselect.sexo = newsex
                        alumnoselect.fecha_nacimiento = newfecha
                        adaptador.notifyDataSetChanged()
                        adaptador.notifyDataSetChanged()
                        var nombre=nombre_selct
                        var idAlumno=obteneridAlumno(nombre)
                        put_alumno(idAlumno,newnombre,newfecha)

                        Snackbar.make(view, "ALUMNO  MODIFICADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                        txt_nombre_actualiza.setText("")
                        txt_fecha_actualiza.setText("")
                        if (btn_sexMact.isChecked()) {
                            btn_sexMact.setChecked(false)

                        }
                        if (btn_sexFact.isChecked()) {
                            btn_sexFact.setChecked(false)

                        }

                    }
                    if (sexo_select == "M") {
                        var newnombre = txt_nombre_actualiza.text.toString()
                        var newsex = "M"
                        var newfecha = txt_fecha_actualiza.text.toString()
                        alumnoselect.nombre = newnombre
                        alumnoselect.sexo = newsex
                        alumnoselect.fecha_nacimiento = newfecha
                        adaptador.notifyDataSetChanged()
                        var nombre=nombre_selct
                        var idAlumno=obteneridAlumno(nombre)
                        put_alumno(idAlumno,newnombre,newfecha)

                        Snackbar.make(view, "ALUMNO  MODIFICADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()

                        txt_nombre_actualiza.setText("")
                        txt_fecha_actualiza.setText("")
                        if (btn_sexMact.isChecked()) {
                            btn_sexMact.setChecked(false)

                        }
                        if (btn_sexFact.isChecked()) {
                            btn_sexFact.setChecked(false)

                        }


                    }


                }

            }
    }

    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                // +1 because January is zero
                val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
                txt_fecha_actualiza.setText(selectedDate)
            })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun put_alumno(
        posicion:Int,
        new_nombre: String,
         new_fecha: String
    ) {
        val url = urlGeneral + "/alumno"+"/"+posicion
        Log.i("url_put",url)

        val parametrosUsuario: List<Pair<String, String>> = listOf(

            "nombre" to new_nombre,

            "fecha_nacimiento" to new_fecha


        )

       var pedido= url.httpPut(parametrosUsuario).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al crear alumno es: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al crear alumno es: ${usuarioString}")
                }
            }
        }

        pedido.join()
    }
    fun obtenerAlumno(): ArrayList<Alumno> {
        val url = urlGeneral + "/alumno"
        var listaAlumnos= arrayListOf<Alumno>()
      var peticion=  url.httpGet().responseString { request, response, result ->
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
                            listaAlumnos.add(
                                Alumno(it.nombre.toString(), it.sexo.toString(),
                                it.fecha_nacimiento.toString(),it.latitud.toString(),it.longitud.toString(),it.url.toString())
                            )

                          //  listaAlumnos.add(Alumno(it.nombre.toString(), it.sexo.toString(),it.fecha_nacimiento.toString()))


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
    fun obteneridAlumno(nombre:String):Int{
        val url = urlGeneral + "/alumno"
        var listaAlumnos = arrayListOf<Alumno>()
        var idAlumno=0
        var peticion=url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val alumnos = Klaxon().parseArray<AlumnoHttp>(data)
                    if (alumnos != null) {
                        alumnos.forEach {
                            Log.i(
                                "http-klaxon", "id:${it.id}"

                            )

                            Log.i(
                                "id", "id:${nombre}"

                            )
                            if(nombre==it.nombre){
                                idAlumno=it.id
                                Log.i(
                                    "id", "id:${idAlumno}"

                                )
                            }

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
        return idAlumno
    }
}