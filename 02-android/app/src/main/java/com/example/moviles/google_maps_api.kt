package com.example.moviles

import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class google_maps_api : AppCompatActivity(),
    OnMapReadyCallback, GoogleMap.OnCameraMoveStartedListener,
GoogleMap.OnCameraMoveListener,
GoogleMap.OnCameraIdleListener,
GoogleMap.OnPolylineClickListener,
GoogleMap.OnPolygonClickListener{

    private lateinit var mMap: GoogleMap
    var tienePermisos=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps_api)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermiso()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //1) Permisos
        //2funcion estable configuacion
        establecerConfiguracionMap(mMap)
        establecerListener(mMap)
        val admin=LatLng(-0.312948, -78.472370)
        val titulo="Administracion zonal los chillos"
        val zoom=17f
        val  puntoUusario=LatLng(-0.312542, -78.477048)
        anadirMarcador(admin,titulo)
        movercamaraZoom(puntoUusario,zoom)

        val poliLineaUno=googleMap.addPolyline(
            PolylineOptions().clickable(true).add(
                LatLng(-0.313796, -78.476968),
                LatLng(-0.314183, -78.478953),
                LatLng(-0.314215, -78.474748),
                LatLng(-0.312101, -78.473471)
            )


        )
        val poligonoUno=googleMap.addPolygon(
            PolygonOptions().clickable(true).add(
                LatLng(-0.320121, -78.474624),
                LatLng(-0.320934, -78.474859),
                LatLng(-0.321033, -78.474225),
                LatLng(-0.320424, -78.474017)
            )
        )
        poligonoUno.fillColor=0xc771c4


        // Add a marker in Sydney and move the camera
       // val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
    fun establecerConfiguracionMap(mapa:GoogleMap){
        val contexto=this.applicationContext
        with(mapa){
            val permisosFineLocation=ContextCompat.checkSelfPermission(contexto,android.Manifest.permission.ACCESS_FINE_LOCATION)
            val tienePermisos=permisosFineLocation==PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled=true
            }
            uiSettings.isZoomControlsEnabled=true
            uiSettings.isMyLocationButtonEnabled=true
        }
    }
    fun solicitarPermiso(){
        val permisosFineLocation=ContextCompat.checkSelfPermission(
            this.applicationContext,android.Manifest.permission.ACCESS_FINE_LOCATION

        )
        val tienePermiso=permisosFineLocation==PackageManager.PERMISSION_GRANTED
        if(tienePermiso){
            Log.i("mapa","tiene permisos FINE LOCATION")
            this.tienePermisos=true
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),1
            )

        }

    }
    fun anadirMarcador(latLng: LatLng,title:String){
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
    }
    fun movercamaraZoom(latLng: LatLng,zoom:Float){
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng,zoom)
        )
    }
    fun establecerListener(mapa:GoogleMap){
        with(mapa){
            setOnCameraIdleListener( this@google_maps_api)
            setOnCameraMoveStartedListener(this@google_maps_api)
            setOnCameraMoveListener(this@google_maps_api)
            setOnPolygonClickListener(this@google_maps_api)
            setOnPolylineClickListener(this@google_maps_api)
        }
    }

    override fun onCameraMoveStarted(p0: Int) {
        Log.i("mapa","empezando a mover")
    }

    override fun onCameraIdle() {
        Log.i("mapa","quieto Oncreate idle")
    }

    override fun onPolylineClick(p0: Polyline?) {
       Log.i("mapa","Poliline ${p0.toString()}")
    }



    override fun onCameraMove() {
        Log.i("mapa","Moviendo")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Log.i("mapa","Poligono ${p0.toString()}")
    }
}