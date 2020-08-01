package com.example.cruapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_crear_alumno.*

class Crear_alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alumno)
        var lista_alumnos= arrayListOf<Alumno>()
        lista_alumnos=ServicioBD.listaAlumnos

        text_fecha.setOnClickListener {
            showDatePickerDialog()
        }

        btn_guarda.setOnClickListener{
            insertaralumno()
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



    }
    fun insertaralumno(){
        var nombre_alumno=txt_nombre.text.toString()
        var sexo:Char=' '
        if(btn_sexF.isChecked()== true){
            sexo='F'

        }
        if(btn_sexM.isChecked()==true){
            sexo='M'

        }
        ServicioBD.crearAlumno(nombre_alumno,sexo, text_fecha.text.toString())

        Log.i("insertar","lista"+ ServicioBD.listaAlumnos.toString())
    }


    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
            text_fecha.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

}