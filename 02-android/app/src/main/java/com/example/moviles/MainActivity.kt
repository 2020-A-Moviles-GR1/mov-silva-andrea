package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_inten_envia_paramtros.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btn_ciclo_vida.setOnClickListener({
            boton -> irCicloVida()
        })


        btn_list_view.setOnClickListener({
                boton -> irListview()
        })
        btn_inten_respuesta.setOnClickListener{

            irAIntenConRespuesta()

        }
       // btn_devolver_respuesta.setOnClickListener {
         //   finish()
       // }
        btn_intent_implicito.setOnClickListener {
            enviarIntentConRespuesta()
        }
        btn_resp_propia.setOnClickListener {
            enviarItenConRespuestPropia()
        }


    }
    fun irCicloVida(){
        val intentExplicito=Intent(
            this,ciclovida::class.java
        )
        this.startActivity(intentExplicito)
    }
    fun irAIntenConRespuesta(){
        val intentExplicito=Intent(
            this,IntenEnviaParamtros::class.java
        )
        intentExplicito.putExtra("numero",2)
        this.startActivity(intentExplicito)
    }
    fun irListview(){
        val intentExplicito=Intent(
            this,BListViewActivity::class.java
        )
        this.startActivity(intentExplicito)
    }
    fun enviarIntentConRespuesta(){
        val intentConRespuesta=Intent(
            Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )
        startActivityForResult(intentConRespuesta,304)
    }

    override fun onActivityResult(requestCode: Int, //numero que enviamos
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            //variables
            Activity.RESULT_OK->{
                Log.i("resultado","ok")
                when(requestCode){
                    304->{
                        val uri=data?.data
                        if(uri != null){
                            val cursor=contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono= cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono= cursor?.getString(indiceTelefono!!)
                            cursor?.close()
                            Log.i("resultado","telefono: ${telefono}")

                        }

                    }
                    305->{
                        if(data != null){
                            val nombre=data.getStringExtra("nombre")
                            val edad=data.getIntExtra("edad",0)
                            Log.i("resultado","Nombre: ${nombre} Edad: ${edad}")
                        }
                    }
                }
            }
            Activity.RESULT_CANCELED->{
                Log.i("resultado",":c")
            }
        }
    }
    fun enviarItenConRespuestPropia(){
        val intentExplicito=Intent(
            this,
            IntenEnviaParamtros::class.java
        )
        startActivityForResult(intentExplicito,305)
    }
}