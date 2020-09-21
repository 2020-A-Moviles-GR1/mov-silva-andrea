package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_v_iew.*

class RecyclerVIewActivity : AppCompatActivity() {
    var numeroLikes=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_v_iew)
        val listaUsuarios= arrayListOf<UsuarioHttp>()

        listaUsuarios.add(
            UsuarioHttp(
                1,
                12324354,
                1291208,
                "1710291839",
                "Carmen",
                "c.lopez@gmail.com",
                "Soltero",
                "password8",
                arrayListOf<PokemonHttp>()
            )
        )


        listaUsuarios.add(
            UsuarioHttp(
                2,
                12324354,
                1291208,
                "1710291716",
                "Miguel",
                "m.zambrano@gmail.com",
                "Soltero",
                "password9",
                arrayListOf<PokemonHttp>()
            )
        )

        listaUsuarios.add(
            UsuarioHttp(
                1,
                12324354,
                1291208,
                "0910283910",
                "Gustavo",
                "g.astudillo@gmail.com",
                "Casado",
                "password10",
                arrayListOf<PokemonHttp>()
            )
        )
        iniciarRecycleView(
            listaUsuarios,this,rv_usuarios
        )
    }
    fun iniciarRecycleView(
      lista:  List<UsuarioHttp>,
        actividad:RecyclerVIewActivity,
      recyclerview:androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorUsiario=RecyclerAdaptador(
            lista,
            actividad,
            recyclerview
        )
        rv_usuarios.adapter=adaptadorUsiario
        rv_usuarios.itemAnimator=androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsiario.notifyDataSetChanged()
    }

    fun anadirLikesEnActividad(numero:Int){
        this.numeroLikes +numero
        tv_titulo_rv.text=numeroLikes.toString()

    }
}