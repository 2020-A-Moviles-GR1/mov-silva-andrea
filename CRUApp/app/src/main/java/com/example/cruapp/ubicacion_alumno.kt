package com.example.cruapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ubicacion_alumno : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    var latitudSuperheroe = 0.00
    var longitudSuperheroe = 0.00
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion_alumno)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        establecerConfiguracionMapa(mMap)
        mMap.setOnMapClickListener {
            anadirMarcador(it)
            Log.i("Mapa", "Latitud: ${it.latitude} longitud: ${it.longitude}")
            enviarLatitudLongitud(it)
        }

    }

    fun anadirMarcador(latLng: LatLng){
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(latLng))
    }

    fun solicitarPermisos(){
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos){
            Log.i("MAPA", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }
    ///////////////////
    fun establecerConfiguracionMapa(mapa:GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }
    //////////////
    fun enviarLatitudLongitud(latLng: LatLng){
        val intentExplicito = Intent(
            this,
            Crear_alumno::class.java
        )
        intentExplicito.putExtra("Lat", latLng.latitude)
        intentExplicito.putExtra("Lng", latLng.longitude)
        startActivity(intentExplicito)
    }
}