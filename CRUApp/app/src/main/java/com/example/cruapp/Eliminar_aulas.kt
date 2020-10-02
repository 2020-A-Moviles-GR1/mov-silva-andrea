package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.example.cruapp.HTTP.AulaHttp
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*
import kotlinx.android.synthetic.main.activity_eliminar_aulas.*
import kotlinx.android.synthetic.main.activity_opciones_aulas.*

class Eliminar_aulas : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.141:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_aulas)
        var lista_aulas: ListView = findViewById(R.id.lv_eliminar_aula)
        var lista_memoria_aula = obtenerAulas()
        var lista_memoria_alumno = obtenerAlumno()

        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria_aula                             //lista
        )
        lista_aulas.setAdapter(adaptador)
        lista_aulas.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("lis-view", "position$position")

                var aulaselect = lista_memoria_aula[position]
                var alumno_elimina = aulaselect.alumnoAsignado
                var posialum= 0
                lista_memoria_alumno.forEach {
                   if(alumno_elimina==it.nombre){
                       posialum=lista_memoria_alumno.indexOf(it)
                   }
                }
                btn_eliminar_aula.setOnClickListener {
                      val alertDialog = AlertDialog.Builder(this).create()
                        alertDialog.setTitle("Alerta de borrado")
                        alertDialog.setMessage("Seguro que desea eliminar el aula")

                        alertDialog.setButton(
                            AlertDialog.BUTTON_POSITIVE, "Yes"
                        ) { dialog, which ->

                            var materia=aulaselect.materia
                            var idAula=obteneridAulas(materia)

                            delete_aula(idAula)

                            var nombre=lista_memoria_alumno.get(position).nombre
                            var idAlumno=obteneridAlumno(nombre)
                            delete_alumno(idAlumno)
                            lista_memoria_alumno.removeAt(posialum)
                            adaptador.notifyDataSetChanged()
                            lista_memoria_aula.removeAt(position)
                            Snackbar.make(view, "AULA  ELIMINADA EXITOSAMENTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show() }

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
    fun obtenerAlumno(): ArrayList<Alumno> {
        val url = urlGeneral + "/alumno"
        var listaAlumnos= arrayListOf<Alumno>()
        var datos_nombre= arrayListOf<String>()
        var ultimo_dato= arrayListOf<String>()
        val peticion= url.httpGet().responseString { request, response, result ->
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

                            listaAlumnos.add(Alumno(it.nombre.toString(), it.sexo.toString(),it.fecha_nacimiento.toString()
                            ,it.latitud.toString(),it.longitud.toString(),it.url.toString()))

                            Log.i("lista_servidor-dentro",listaAlumnos.toString())


                            /*if(it.pokemons!!.size > 0 ){
                                it.pokemons!!.forEach{
                                    Log.i("http-klaxon","Nombre:${it.nombre}")
                                }
                            }*/

                        }



                    }
                    listaAlumnos.forEach{
                        var nombrealum=it.nombre
                        datos_nombre.add(nombrealum)
                        Log.i("alumno",datos_nombre.toString())

                    }
                    ultimo_dato= datos_nombre
                    Log.i("alumno-ultimo",ultimo_dato.toString())
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }

            }

        }
        peticion.join()
        Log.i("nombre_servidor",ultimo_dato.toString())

        return listaAlumnos

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
    fun delete_aula(
        posicion: Int

    ) {
        val url = urlGeneral + "/aula" + "/" + posicion
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
    fun obteneridAulas(materia:String): Int {
        val url = urlGeneral + "/aula"
        var listaAulas= arrayListOf<Aulas>()
        var idAula=0
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
                            if(materia==it.materia){
                                idAula=it.id
                                Log.i(
                                    "id", "id:${idAula}"

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
        return idAula

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