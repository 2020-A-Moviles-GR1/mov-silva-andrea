package com.example.cruapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_eliminar_alumno.*
import kotlinx.android.synthetic.main.activity_eliminar_aulas.*
import kotlinx.android.synthetic.main.activity_opciones_aulas.*

class Eliminar_aulas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar_aulas)
        var lista_aulas: ListView = findViewById(R.id.lv_eliminar_aula)
        var lista_memoria_aula = ServicioBD.listaAulas
        var lista_memoria_alumno = ServicioBD.listaAlumnos

        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, //nombre layout
            lista_memoria_aula                             //lista
        )
        lista_aulas.setAdapter(adaptador)
        lista_aulas.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.i("lis-view", "position$position")

                var aulaselect = lista_memoria_aula[position]
                var alumno_elimina = aulaselect.alumnoAsignado
                var posialum: Int = 0
                lista_memoria_alumno.forEach {
                    if (alumno_elimina == it.nombre) {
                        posialum = lista_memoria_alumno.indexOf(it)
                    }
                }
                btn_eliminar_aula.setOnClickListener {



                        val alertDialog = AlertDialog.Builder(this).create()
                        alertDialog.setTitle("Alerta de borrado")
                        alertDialog.setMessage("Seguro que desea eliminar el aula")

                        alertDialog.setButton(
                            AlertDialog.BUTTON_POSITIVE, "Yes"
                        ) { dialog, which ->
                            lista_memoria_aula.removeAt(position)
                            lista_memoria_alumno.removeAt(posialum)
                            adaptador.notifyDataSetChanged()

                            Snackbar.make(view, "AULA  ELIMINADA EXITOSAMENTE", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show() }

                        alertDialog.setButton(
                            AlertDialog.BUTTON_NEGATIVE, "No"
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