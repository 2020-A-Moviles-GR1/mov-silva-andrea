package com.example.cruapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class Buscar_alumno : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_alumno)


        var lista_alumnos:ListView= findViewById(R.id.lv_buscar)

        var lista_memoria=ServicioBD.listaAlumnos
        var nomalum:EditText= findViewById(R.id.txt_busca)
        val adaptador= ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria                             //lista
        )
        var mSearchTw = object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                adaptador.getFilter().filter(s)
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {}
        }

            lista_alumnos.setAdapter(adaptador)
            nomalum.addTextChangedListener(mSearchTw)



    }


}