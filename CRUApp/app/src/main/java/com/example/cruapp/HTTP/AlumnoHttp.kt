package com.example.cruapp.HTTP

import android.os.Parcel
import android.os.Parcelable

class AlumnoHttp (
    var id:Int,
    var nombre:String?,
    var sexo:String?,
    var fecha_nacimiento: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        (parcel.readValue(Char::class.java.classLoader) as? Char).toString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeValue(sexo)
        parcel.writeString(fecha_nacimiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlumnoHttp> {
        override fun createFromParcel(parcel: Parcel): AlumnoHttp {
            return AlumnoHttp(parcel)
        }

        override fun newArray(size: Int): Array<AlumnoHttp?> {
            return arrayOfNulls(size)
        }
    }

}