package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
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
               val alertDialog = AlertDialog.Builder(this).create()
               alertDialog.setTitle("ALERTA")
               alertDialog.setMessage("Seguro que desea eleiminar al alumno")

               alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes"
               ) { dialog, which ->
                   lista_memoria.removeAt(position)
                   adaptador.notifyDataSetChanged()
                   Snackbar.make(view, "ALUMNO  ELIMINADO EXITOSAMENTE", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show() }

               alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"
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



}