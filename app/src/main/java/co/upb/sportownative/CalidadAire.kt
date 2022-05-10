package co.upb.sportownative

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Half.toFloat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_home.*

class CalidadAire : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calidad_aire)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        setup(email ?:"")

        //Traer datos del shared preference
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val nombreusuario = prefs.getString("nombrecompleto", null).toString()
        textViewUser.setText(nombreusuario)

        //mapa
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)



        //Recordar borrar datos al cerrar sesión
    }
    override fun onMapReady(googleMap: GoogleMap) {
        //geo localizacion
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 181)

        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location ->
            val lat = location.latitude
            val lon = location.longitude
            val ubication = LatLng(lat,lon)
            googleMap.addMarker(
                MarkerOptions()
                    .position(ubication)
                    .title("AQI: 20")
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubication, 15F))
        }

    }
    private fun setup(email: String){

        title = "Inicio"



    }
}