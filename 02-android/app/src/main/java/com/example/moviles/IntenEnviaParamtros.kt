package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_inten_envia_paramtros.*

class IntenEnviaParamtros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inten_envia_paramtros)
        //Propiedad intent

        val numeroEncontrado=intent.getIntExtra("numero",0)
        Log.i("intents","El numero encontrado es: ${numeroEncontrado}")
        val textoCompartido:String?=intent.getStringExtra(Intent.EXTRA_TEXT)

        if(textoCompartido != null){
            Log.i("intents","El texto es: ${textoCompartido}")
        }
        btn_devolver_respuesta.setOnClickListener {
           finish()
         }
        btn_resp_aceptar.setOnClickListener {
            val nombre="Andrea"
            val edad=22
            val intentRespuesta=Intent()
            intentRespuesta.putExtra("nombre",nombre)
            intentRespuesta.putExtra("edad",edad)
            //this.
            setResult(
                Activity.RESULT_OK,
                intentRespuesta
            )
            finish()
        }
        btn_resp_cancelar.setOnClickListener {
            val intentcANCELADO=Intent()
            setResult(
                Activity.RESULT_CANCELED,
                intentcANCELADO
            )
            finish()
        }
    }
}