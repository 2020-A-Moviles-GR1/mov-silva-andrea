package com.example.moviles

class ServivioBDDMemoria{
    //guardar clase o metodos
    //una sola intacia de esa clase
    //todo trbaja en memoria
    companion object{
        var numero=0
        fun aniadirNumero(){
            this.numero=this.numero+1
        }
    }
}