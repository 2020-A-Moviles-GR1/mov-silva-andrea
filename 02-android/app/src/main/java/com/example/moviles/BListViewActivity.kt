package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_view.*

class BListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)
        val listaEntrenadores= arrayListOf<Entrenador>()
        listaEntrenadores.add(Entrenador("Andrea","Silva"))
        listaEntrenadores.add(Entrenador("Jose","Ruiz"))
        listaEntrenadores.add(Entrenador("Marcelo","Andrade"))
        listaEntrenadores.add(Entrenador("Alejandra","Muñoz"))
        listaEntrenadores.add(Entrenador("Mauricio","Sanchez"))
        listaEntrenadores.add(Entrenador("Milena","Garcia"))
        //uso de adaptadores
        val adaptador=ArrayAdapter(
            this,android.R.layout.simple_list_item_1, //nombre layout
            listaEntrenadores                              //lista
        )
        lv_numero.adapter=adaptador
        lv_numero.onItemClickListener=AdapterView.OnItemClickListener{
            parent, view, position, id -> Log.i("lis-view","position$position")
        }

        btn_anadir_entrenador.setOnClickListener{
            aniadirEntrenador(adaptador,
            listaEntrenadores)
        }


    }
    fun aniadirEntrenador(adaptador:ArrayAdapter<Entrenador>, listaentrenadores:ArrayList<Entrenador>){
        listaentrenadores.add(Entrenador("Nuevo","Entrenador"))
        adaptador.notifyDataSetChanged()
    }
}