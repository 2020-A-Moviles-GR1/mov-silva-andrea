package com.example.cruapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.github.kittinunf.fuel.httpPost
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import com.github.kittinunf.result.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_crear_alumno.view.*

class Crear_alumno : AppCompatActivity() {
    //val urlGeneral = "http://192.168.1.134:1337"
    val urlGeneral = "http://192.168.1.141:1337"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alumno)

        var imagenes1:ImageView = findViewById(R.id.imagen1)
        var imagenes2:ImageView = findViewById(R.id.imagen2)
        var imagenes3:ImageView =findViewById(R.id.imagen3)

        text_fecha.setOnClickListener {
            showDatePickerDialog()
        }

        Glide.with(this)
            .load("https://icon-icons.com/icons2/665/PNG/128/alien_icon-icons.com_60286.png")
            .into(imagenes1)

        Glide.with(this)
            .load("https://icon-icons.com/icons2/2248/PNG/128/pirate_icon_138281.png")
            .into(imagenes2)

        Glide.with(this)
            .load("https://icon-icons.com/icons2/1695/PNG/128/10874mermaidlightskintone_111987.png")
            .into(imagenes3)

        var arrayAlumnos = ServicioBD.listaAlumnos
        arrayAlumnos.forEach {
            Log.i("Alumno", "Alumno: ${it}")
            txt_nombre.setText(it.nombre)
            if(it.sexo=="F"){
                btn_sexF.setChecked(true)
            }
            if (it.sexo=="M"){
                btn_sexM.setChecked(true)
            }
             text_fecha.setText( it.fecha_nacimiento)
                 if(it.url=="https://cdn.icon-icons.com/icons2/665/PNG/512/alien_icon-icons.com_60286.png"){
                slct_imagen1.setChecked(true)
            }
                if (it.url=="https://icon-icons.com/icons2/2248/PNG/128/pirate_icon_138281.png"){
                    slct_imagen2.setChecked(true)
                }
                if(it.url=="https://icon-icons.com/icons2/1695/PNG/128/10874mermaidlightskintone_111987.png"){
                    slct_imagen3.setChecked(true)
                }




        }
        btn_guarda.setOnClickListener{
           // insertaralumno()
            post_alumno()
             Snackbar.make(it, "ALUMNO  GUARDADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            txt_nombre.setText("")
            text_fecha.setText("")
            if (btn_sexM.isChecked()){
                btn_sexM.setChecked(false)
            }
            if (btn_sexF.isChecked()){
                btn_sexF.setChecked(false)
            }
           
        }
        btn_ubicacion.setOnClickListener{
            ir_mapa()

        }

    }

    fun insertaralumno(){
        var nombre_alumno=txt_nombre.text.toString()
        var sexo:String=""
        if(btn_sexF.isChecked()== true){
            sexo="F"
        }
        if(btn_sexM.isChecked()==true){
            sexo="M"
        }
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
            text_fecha.setText(selectedDate)
        })
        newFragment.show(supportFragmentManager, "datePicker")
    }
    fun post_alumno(){
        val url = urlGeneral + "/alumno"
        var sexo:String=""
        var urlImagen:String=""
        if(btn_sexF.isChecked()== true){
            sexo="F"
        }
        if(btn_sexM.isChecked()==true){
            sexo="M"

        }
        text_fecha.setOnClickListener {
            showDatePickerDialog()
        }


       if(slct_imagen1.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/665/PNG/128/alien_icon-icons.com_60286.png"

        }
        if (slct_imagen2.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/2248/PNG/128/pirate_icon_138281.png"
        }
        if(slct_imagen3.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/1695/PNG/128/10874mermaidlightskintone_111987.png"
        }





        Log.i("imagen",urlImagen)

        val parametrosUsuario: List<Pair<String, Any>> = listOf(
            "nombre" to txt_nombre.text.toString(),
            "sexo" to sexo,
            "fecha_nacimiento" to text_fecha.text.toString(),
            "latitud" to intent.getDoubleExtra("Lat",0.00),
            "longitud" to intent.getDoubleExtra("Lng",0.00),
            "url" to urlImagen

        )
        url.httpPost(parametrosUsuario).responseString { request, response, result ->
            when(result){
                is Result.Failure -> {
                    val error = result.getException()
                    Log.i("Error", "El error al crear alumno es: ${error}")
                }
                is Result.Success ->{
                    val usuarioString = result.get()
                    Log.i("Exitoso", "El exito al crear alumno es: ${usuarioString}")
                }
            }
        }
    }

    fun ir_mapa(){
        var urlImagen:String=""
        var sexo:String=""
       if(slct_imagen1.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/665/PNG/128/alien_icon-icons.com_60286.png"
        }
        if (slct_imagen2.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/2248/PNG/128/pirate_icon_138281.png"
        }
        if(slct_imagen3.isChecked()==true){
            urlImagen="https://icon-icons.com/icons2/1695/PNG/128/10874mermaidlightskintone_111987.png"
        }

        if(btn_sexM.isChecked()==true){
            sexo="M"

        }
        if(btn_sexF.isChecked()==true){
            sexo="F"

        }

       ServicioBD.listaAlumnos.add(Alumno(txt_nombre.text.toString(),sexo, text_fecha.text.toString(),"0.00","0.00",urlImagen))
        Log.i("insertar","lista"+ ServicioBD.listaAlumnos.toString())
        /*ServicioBD.listaAlumnos.forEach {
            it.nombre=txt_nombre.toString()
            it.fecha_nacimiento=text_fecha.toString()
            if(it.sexo=="M"){
                btn_sexM.isChecked=true
            }
            if(it.sexo=="F"){
                btn_sexF.isChecked=true
            }
        }*/

        val  intentExplicito= Intent(
            this,ubicacion_alumno::class.java
        )
        this.startActivity(intentExplicito)
    }
}