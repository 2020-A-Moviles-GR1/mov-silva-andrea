package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.example.cruapp.HTTP.AulaHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_alumno.*
import kotlinx.android.synthetic.main.activity_actualizar_aula.*
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_crear_aulas.*
import kotlinx.android.synthetic.main.activity_opciones_aulas.*

class Actualizar_aula : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.134:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_aula)

        var lista_aulas: ListView = findViewById(R.id.lv_actualiza_aulas)
        var lista_memoria=obtenerAulas()

        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        lista_aulas.setAdapter(adaptador)


        lista_aulas.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id -> Log.i("lis-view","position$position")
            var listaalumsp: Spinner =findViewById(R.id.sp_alumnoactuliza)
            var listanumsp: Spinner =findViewById(R.id.sp_numalumnos_actualiza)
            var aulasselect=lista_memoria[position]
            var materia_selct= aulasselect.materia
            txt_materia_actualizar.setText(materia_selct)
            var numalumselect=aulasselect.numalumnos



            var numeros:ArrayList<String> = arrayListOf("5","10","15","20","25","30","35","40","45","50","55"
                ,"60","65","70","75","80","85","90","95","100")


            val adaptadornum= ArrayAdapter(
                this,android.R.layout.simple_spinner_item, //nombre layout
                numeros                           //lista
            )
            listanumsp.setAdapter(adaptadornum)
            var posicionnum:Int=0
            numeros.forEach {
                if(numalumselect == it){

                    posicionnum=numeros.indexOf(it)
                }
            }
            sp_numalumnos_actualiza.setSelection(posicionnum,true)

           var salonDisponible=aulasselect.salonDisponible
            if(salonDisponible=="Si"){
                btn_actualizasi.setChecked(false)

            }

            if(salonDisponible=="No"){
                btn_actualizano.setChecked(false)

            }
            var alumselect=aulasselect.alumnoAsignado
            var alumnosdisponibles=obtenerAlumno()
            var datos_nombre:ArrayList<String> = arrayListOf()
            var posicioalum:Int=0
            alumnosdisponibles.forEach {
               datos_nombre.add(it.nombre)


            }
            datos_nombre.forEach {
                if(alumselect== it){
                    posicioalum=datos_nombre.indexOf(it)
                }
            }


            val adaptadordatos= ArrayAdapter(
                this,android.R.layout.simple_spinner_item, //nombre layout
                datos_nombre                           //lista
            )
            listaalumsp.setAdapter(adaptadordatos)
            sp_alumnoactuliza.setSelection(posicioalum,true)
            sp_alumnoactuliza.setEnabled(false)

            btn_actualiza_aulas.setOnClickListener {

                if(salonDisponible=="Si"){

                    var materia=aulasselect.materia
                    var idAula=obteneridAulas(materia)

                    var newmateria=txt_materia_actualizar.text.toString()
                    var newnumalum=sp_numalumnos_actualiza.getSelectedItem().toString()
                    var newsalon:String=""
                    if(btn_actualizasi.isChecked()== true){
                        newsalon="Si"
                    }
                    if(btn_actualizano.isChecked()==true){
                        newsalon="no"
                    }
                    var newalum=sp_alumnoactuliza.getSelectedItem().toString()
                    aulasselect.materia=newmateria
                    aulasselect.numalumnos=newnumalum
                    aulasselect.salonDisponible=newsalon
                    aulasselect.alumnoAsignado=newalum
                    adaptador.notifyDataSetChanged()
                    put_aula(idAula,newmateria,newnumalum,newsalon,newalum)
                    Snackbar.make(view, "AULA  MODIFICADA EXITOSAMENTE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    txt_materia_actualizar.setText("")

                    if (btn_actualizasi.isChecked()){
                        btn_actualizasi.setChecked(false)

                    }
                    if (btn_actualizano.isChecked()){
                        btn_actualizano.setChecked(false)

                    }


                }
                if(salonDisponible=="No"){

                    var newmateria=txt_materia_actualizar.text.toString()
                    var newnumalum=sp_numalumnos_actualiza.getSelectedItem().toString()
                    var newsalon:String=""
                    if(btn_actualizasi.isChecked()== true){
                        newsalon="Si"
                    }
                    if(btn_actualizano.isChecked()==true){
                        newsalon="No"
                    }
                    var newalum=sp_alumnoactuliza.getSelectedItem().toString()
                    aulasselect.materia=newmateria
                    aulasselect.numalumnos=newnumalum
                    aulasselect.salonDisponible=newsalon
                    aulasselect.alumnoAsignado=newalum
                    adaptador.notifyDataSetChanged()
                    Snackbar.make(view, "AULA  MODIFICADA EXITOSAMENTE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    txt_materia_actualizar.setText("")

                    if (btn_actualizasi.isChecked()){
                        btn_actualizasi.setChecked(false)

                    }
                    if (btn_actualizano.isChecked()){
                        btn_actualizano.setChecked(false)

                    }


                }


            }

        }

    }
    fun put_aula(
        posicion:Int,
        new_materia: String,
        new_numalum: String, new_salon: String,newalumn:String
    ) {
        val url = urlGeneral + "/aula"+"/"+posicion
        Log.i("url_put",url)

        val parametrosUsuario: List<Pair<String, String>> = listOf(

            "materia" to new_materia,
            "numAlumnos" to new_numalum,
            "salonDisponible" to new_salon,
            "alumnoasignado" to newalumn


        )

        url.httpPut(parametrosUsuario).responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al actualizar aula es: ${error}")
                }
                is Result.Success -> {
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al actualizar aula  es: ${usuarioString}")
                }
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

                            listaAlumnos.add(Alumno(it.nombre.toString(), it.sexo.toString(),it.fecha_nacimiento.toString()))

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
}
