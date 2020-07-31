package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*

class Eliminar_alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_alumno)

        var lista_alumnos: ListView = findViewById(R.id.lv_eliminar)
        var lista_memoria=ServicioBD.listaAlumnos

        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        lista_alumnos.setAdapter(adaptador)
        lista_alumnos.onItemClickListener= AdapterView.OnItemClickListener{
                parent, view, position, id -> Log.i("lis-view","position$position")
           btn_eliminar.setOnClickListener {
               lista_memoria.removeAt(position)
               adaptador.notifyDataSetChanged()
           }

        }


    }
}