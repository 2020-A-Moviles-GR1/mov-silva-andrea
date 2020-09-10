package com.example.cruapp

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_crear_aulas.*


class Crear_aulas : AppCompatActivity() {
    val urlGeneral = "http://192.168.1.134:1337"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_aulas)

        var listaalum:Spinner=findViewById(R.id.sp_alumno)
        var listanum:Spinner=findViewById(R.id.sp_numalu)
        //var lista_memoria=obtenerAlumno()
        var datos_nombre:ArrayList<String> =obtenerAlumno()
        var numeros:ArrayList<String> = arrayListOf("5","10","15","20","25","30","35","40","45","50","55"
            ,"60","65","70","75","80","85","90","95","100")

        Log.i("nombres-get",datos_nombre.toString())
        val adaptadordatos= ArrayAdapter(
            this,android.R.layout.simple_spinner_item, //nombre layout
            datos_nombre                     //lista

        )

        listaalum.setAdapter(adaptadordatos)

        val adaptadornum= ArrayAdapter(
            this,android.R.layout.simple_spinner_item, //nombre layout
            numeros                           //lista
        )

        listanum.setAdapter(adaptadornum)

        btn_guardar_aula.setOnClickListener{
            post_aula()
            Snackbar.make(it, "AULA  GUARDADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            txt_materia.setText("")

            if (btn_si.isChecked()){
                btn_si.setChecked(false)

            }
            if (btn_no.isChecked()){
                btn_no.setChecked(false)

            }

        }


    }


    fun insertaraulas(){
        var materia=txt_materia.text.toString()
        var numalumnos=sp_numalu.getSelectedItem().toString();
        var salonDisponible:String=""
        if(btn_no.isChecked()== true){
            salonDisponible="No"
            btn_si.setChecked(false)
        }
        if(btn_si.isChecked()==true){
            salonDisponible="Si"
            btn_si.setChecked(false)
        }
        var alumnoasignado=sp_alumno.getSelectedItem().toString();


        ServicioBD.crearAulas(materia,numalumnos.toString(),salonDisponible,alumnoasignado)

        Log.i("insertar","lista"+ ServicioBD.listaAulas.toString())
    }
    fun post_aula(){
        val url = urlGeneral + "/aula"
        var materia=txt_materia.text.toString()
        var numalumnos=sp_numalu.getSelectedItem().toString();
        var salonDisponible:String=""
        if(btn_no.isChecked()== true){
            salonDisponible="No"
            btn_si.setChecked(false)
        }
        if(btn_si.isChecked()==true){
            salonDisponible="Si"
            btn_si.setChecked(false)
        }
        var alumnoasignado=sp_alumno.getSelectedItem().toString();

        val parametrosUsuario: List<Pair<String, String>> = listOf(

            "materia" to materia,
            "numAlumnos" to numalumnos,
            "salonDisponible" to salonDisponible,
            "alumnoasignado" to alumnoasignado


        )

       val peticion= url.httpPost(parametrosUsuario).responseString { request, response, result ->
            when(result){
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al crear aula es: ${error}")
                }
                is Result.Success ->{
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al crear aula es: ${usuarioString}")
                }
            }
        }
        peticion.join()
    }
    fun obtenerAlumno(): ArrayList<String> {
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

        return ultimo_dato

    }
}