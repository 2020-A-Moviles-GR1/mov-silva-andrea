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
import kotlinx.android.synthetic.main.activity_actualizar_alumno.*
import kotlinx.android.synthetic.main.activity_crear_alumno.*
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*

class Actualizar_alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_alumno)
        var lista_alumnos: ListView = findViewById(R.id.lv_actualizar)
        var lista_memoria=ServicioBD.listaAlumnos

        txt_fecha_actualiza.setOnClickListener {
            showDatePickerDialog()
        }


        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        lista_alumnos.setAdapter(adaptador)

        lista_alumnos.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id -> Log.i("lis-view","position$position")
            var alumnoselect=lista_memoria[position]
            var nombre_selct= alumnoselect.nombre
            txt_nombre_actualiza.setText(nombre_selct)
            var sexo_select=alumnoselect.sexo
            btn_sexFact.setEnabled(false);
            btn_sexMact.setEnabled(false);
            if(sexo_select=='F'){
                btn_sexFact.setChecked(true)

            }

            if(sexo_select=='M'){
                btn_sexMact.setChecked(true)

            }
            var fecha_select=alumnoselect.fecha_nacimiento
            txt_fecha_actualiza.setText(fecha_select)

            btn_actualizar.setOnClickListener {

                if(sexo_select=='F'){
                    var newnombre=txt_nombre_actualiza.text.toString()
                    var newsex='F'
                    var newfecha=txt_fecha_actualiza.text.toString()
                   alumnoselect.nombre=newnombre
                    alumnoselect.sexo=newsex
                    alumnoselect.fecha_nacimiento=newfecha
                    adaptador.notifyDataSetChanged()

                }
                if(sexo_select=='M'){
                    var newnombre=txt_nombre_actualiza.text.toString()
                    var newsex='M'
                    var newfecha=txt_fecha_actualiza.text.toString()
                    alumnoselect.nombre=newnombre
                    alumnoselect.sexo=newsex
                    alumnoselect.fecha_nacimiento=newfecha
                    adaptador.notifyDataSetChanged()

                }


            }

        }
    }
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
            txt_fecha_actualiza.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
}