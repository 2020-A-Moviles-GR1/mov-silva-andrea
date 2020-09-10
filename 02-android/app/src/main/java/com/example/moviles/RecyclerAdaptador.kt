package com.example.moviles

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerAdaptador(private val listaUsuarios: List<UsuarioHttp>,
                        private val contexto:RecyclerVIewActivity,
                        private val recyclerView:androidx.recyclerview.widget.RecyclerView
):androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {
inner class  MyViewHolder(view:View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view){
    var nombreTextView: TextView
    var cedulaTextView:TextView
    var acctionButoon:Button
    var likesTextView:TextView
    var numeroLikes=0

    init {
        nombreTextView=view.findViewById(R.id.tv_nombre)
        cedulaTextView=view.findViewById(R.id.tv_cedula)
        acctionButoon=view.findViewById(R.id.btn_accion)
        likesTextView=view.findViewById(R.id.tv_likes)
        acctionButoon.setOnClickListener {
            this.anadirLike()
        }

    }
    fun anadirLike(){
        this.numeroLikes = this.numeroLikes + 1
        likesTextView.text = this.numeroLikes.toString()
        contexto.anadirLikesEnActividad(1)
    }
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptador.MyViewHolder {
                //definimos la interfaz de vamos a usar
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.adaptador_persona,parent,false)// recursos del adaptador
        return  MyViewHolder(itemView)
    }

   //#itmes que tenemos en la lsita
    override fun getItemCount(): Int {
        return listaUsuarios.size   }


    //funcion que se ejcutara con cad auno de las items
    override fun onBindViewHolder(
        holder: MyViewHolder, //clase implementada
        position: Int) {//posision
        val usuario=listaUsuarios[position]
        holder.nombreTextView.text = usuario.nombre
        holder.cedulaTextView.text = usuario.cedula
        holder.acctionButoon.text = "Like ${usuario.nombre}"
        holder.likesTextView.text="0"

    }
}