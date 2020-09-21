package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*

class Eliminar_alumno : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.13:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_eliminar_alumno)

        var lista_alumnos: ListView = findViewById(R.id.lv_eliminar)
        var lista_memoria = obtenerAlumno()

        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        lista_alumnos.setAdapter(adaptador)
        lista_alumnos.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("lis-view", "position$position")
                btn_eliminar.setOnClickListener {
                    val alertDialog = AlertDialog.Builder(this).create()
                    alertDialog.setTitle("ALERTA")
                    alertDialog.setMessage("Seguro que desea eleiminar al alumno")

                    alertDialog.setButton(
                        AlertDialog.BUTTON_POSITIVE, "Yes"
                    ) { dialog, which ->
                        lista_memoria.removeAt(position)
                        adaptador.notifyDataSetChanged()
                        var nombre=lista_memoria.get(position).nombre
                        var idAlumno=obteneridAlumno(nombre)
                        delete_alumno(idAlumno)
                        Snackbar.make(view, "ALUMNO  ELIMINADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }

                    alertDialog.setButton(
                        AlertDialog.BUTTON_NEGATIVE, "No"
                    ) { dialog, which -> dialog.dismiss() }
                    alertDialog.show()

                    val btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    val btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)

                    val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
                    layoutParams.weight = 10f
                    btnPositive.layoutParams = layoutParams
                    btnNegative.layoutParams = layoutParams

                }

            }


    }


    fun delete_alumno(
        posicion: Int

    ) {
        val url = urlGeneral + "/alumno" + "/" + posicion
        Log.i("url_put", url)



        url.httpDelete().responseString { request, response, result ->
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
    }

    fun obtenerAlumno(): ArrayList<Alumno> {
        val url = urlGeneral + "/alumno"
        var listaAlumnos = arrayListOf<Alumno>()
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

                            listaAlumnos.add(
                                Alumno(
                                    it.nombre.toString(),
                                    it.sexo.toString(),
                                    it.fecha_nacimiento.toString(),
                                    it.latitud.toString(),
                                    it.longitud.toString(),
                                    it.url.toString()
                                )
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

