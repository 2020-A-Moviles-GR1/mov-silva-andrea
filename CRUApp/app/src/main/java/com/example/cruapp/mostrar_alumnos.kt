package com.example.cruapp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.beust.klaxon.Klaxon
import com.example.cruapp.HTTP.AlumnoHttp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.koushikdutta.ion.Ion
import java.util.concurrent.ExecutionException


class mostrar_alumnos : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val urlGeneral = "http://192.168.1.141:1337"
    var tienePermisos = false
    var urlString: ArrayList<String> = arrayListOf()
    private var markeralumno: Marker? = null


    // var superheroes: ArrayList<SuperheroeHttp> = arrayListOf()
    var listaImagenes = arrayListOf<String>()
    var imagenURL:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_alumnos)
        solicitarPermisos()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var arrayAlumnos = obtenerAlumno()
        var url = arrayListOf<Uri>()
        arrayAlumnos.forEach {
            var latitud = it.latitud.toString().toDouble()
            var longitud = it.longitud.toString().toDouble()
            imagenURL = it.url.toString()

            var latLng = LatLng(latitud, longitud)
            var nombre = it.nombre.toString()
            anadirMarcador(latLng, imagenURL, nombre)
            val uri: Uri = Uri.parse(it.url)
            url.add(uri)
            mMap.setOnMarkerClickListener {
                var  intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.getSnippet()))
                startActivity(intent)

                true


            }

            // marcadorConURL()
            //Log.i("marcador","Salio marcador")
        }

    }

    fun obtenerAlumno(): ArrayList<Alumno> {
        val url = urlGeneral + "/alumno"
        var listaAlumnos= arrayListOf<Alumno>()
        var peticion=url.httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http-klaxon", "Data ${data}")
                    val alumnos = Klaxon().parseArray<AlumnoHttp>(data)
                    if (alumnos != null) {
                        alumnos.forEach {
                            Log.i(
                                "http-klaxon", "Nombre:${it.nombre}"

                            )

                            listaAlumnos.add(Alumno(it.nombre.toString(), it.sexo.toString(),it.fecha_nacimiento.toString(),it.latitud.toString(),it.longitud.toString(),it.url.toString()))


                            /*if(it.pokemons!!.size > 0 ){
                                it.pokemons!!.forEach{
                                    Log.i("http-klaxon","Nombre:${it.nombre}")
                                }
                            }*/

                        }


                    }
                }
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http_klaxon", "error:${ex.message}")
                }

            }

        }
        peticion.join()
        return listaAlumnos

    }



    fun anadirMarcador(latLng: LatLng, url: String, nombre:String) {
        val origen = LatLng(-0.232283, -78.513623)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origen, 12F))
        try {
            val bmImg: Bitmap = Ion.with(this).load(url).asBitmap().get()
            markeralumno=   mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory
                        .fromBitmap(bmImg))
                    .title(nombre).snippet(url)
            )





        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }

    fun solicitarPermisos() {
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos) {
            Log.i("MAPA", "Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }
    }

    fun establecerConfiguracionMapa(mapa: GoogleMap) {
        val contexto = this.applicationContext
        with(mapa) {
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos) {
                mapa.isMyLocationEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }



    fun marcadorConURL(){
        var latLng =LatLng(-0.232283, -78.513623)

        try {
            Log.i("marcador","entrando al marcador")
            val bmImg: Bitmap = Ion.with(this)
                .load("https://cdn.icon-icons.com/icons2/665/PNG/512/alien_icon-icons.com_60286.png").asBitmap().get()
            mMap.addMarker(
                MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(bmImg))
            )
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }




}