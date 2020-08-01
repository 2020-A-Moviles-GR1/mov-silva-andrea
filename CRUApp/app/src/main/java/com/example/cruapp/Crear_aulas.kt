package com.example.cruapp

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_crear_aulas.*


class Crear_aulas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_aulas)

        var listaalum:Spinner=findViewById(R.id.sp_alumno)
        var listanum:Spinner=findViewById(R.id.sp_numalu)
        var lista_memoria=ServicioBD.listaAlumnos
        var datos_nombre:ArrayList<String> = arrayListOf()
        var numeros:ArrayList<String> = arrayListOf("5","10","15","20","25","30","35","40","45","50","55"
            ,"60","65","70","75","80","85","90","95","100")
        var datos_num:ArrayList<String> = arrayListOf()

        lista_memoria.forEach{
            var nombrealum=it.nombre

            datos_nombre.add(nombrealum)

        }
        val adaptadordatos= ArrayAdapter(
            this,android.R.layout.simple_spinner_item, //nombre layout
            datos_nombre                            //lista
        )
        listaalum.setAdapter(adaptadordatos)

        val adaptadornum= ArrayAdapter(
            this,android.R.layout.simple_spinner_item, //nombre layout
            numeros                           //lista
        )

        listanum.setAdapter(adaptadornum)

        btn_guardar_aula.setOnClickListener{
            insertaraulas()
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
}