package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_actualizar_alumno.*
import kotlinx.android.synthetic.main.activity_actualizar_aula.*
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_crear_aulas.*
import kotlinx.android.synthetic.main.activity_opciones_aulas.*

class Actualizar_aula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_aula)

        var lista_aulas: ListView = findViewById(R.id.lv_actualiza_aulas)
        var lista_memoria=ServicioBD.listaAulas

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
            var alumnosdisponibles=ServicioBD.listaAlumnos
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
}