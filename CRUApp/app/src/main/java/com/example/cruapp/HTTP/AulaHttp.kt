package com.example.cruapp.HTTP

import android.os.Parcel
import android.os.Parcelable

class AulaHttp(
    var id:Int,
    var materia:String?,
    var numAlumnos:Int?,
    var salonDisponible:String?,
    var alumnoasignado: String?

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(materia)
        parcel.writeString(numAlumnos.toString())
        parcel.writeString(salonDisponible)
        parcel.writeString(alumnoasignado)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AulaHttp> {
        override fun createFromParcel(parcel: Parcel):AulaHttp {
            return AulaHttp(parcel)
        }

        override fun newArray(size: Int): Array<AulaHttp?> {
            return arrayOfNulls(size)
        }
    }

}