package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_ciclovida.*

class ciclovida : AppCompatActivity() {
    var numeroActual=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclovida)
        Log.i("Activity","OnCreate")
        numeroActual= ServivioBDDMemoria.numero
        if(numeroActual !=0){
            tv_num.text=numeroActual.toString()
        }
        btn_añadir.setOnClickListener{
            sumarValor()}
    }
    fun sumarValor(){
        numeroActual=numeroActual+1
        ServivioBDDMemoria.aniadirNumero()
        tv_num.text=numeroActual.toString()
    }
    override fun onStart() {
        super.onStart()
        Log.i("Activity","OnStart")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Activity","OnRestart")


    }

    override fun onResume() {
        super.onResume()
        Log.i("Activity","OnResume")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity","OnPause")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity","OnStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity","OnDestroy")

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Activity", "onSaveInstanceState")
        outState?.run {
            putInt("numeroActualGuardado",numeroActual)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Activity", "onRestoreInstancesState")
        val valorRecuoerdo=savedInstanceState
            ?.getInt("numeroActualGuardado")
        if (valorRecuoerdo != null){
            this.numeroActual=valorRecuoerdo
            tv_num.text=this.numeroActual.toString()
        }
    }
}