package pt.iade.ei.bestumbrella1.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pt.iade.ei.bestumbrella1.R
import pt.iade.ei.bestumbrella1.models.RentalStation

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    
    // Lista de estações de aluguer de guarda-chuva
    private val rentalStations = listOf(
        RentalStation(1, "Metro Moscavide", 38.7687, -9.0974, 8, 10),
        RentalStation(2, "Metro Oriente", 38.7689, -9.0942, 4, 8),
        RentalStation(3, "Parque das Nações Norte", 38.7715, -9.0980, 6, 10),
        RentalStation(4, "IADE", 38.7633, -9.0941, 3, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Inicializar o mapa
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Inicializar o provedor de localização
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Configurar o botão de localização
        findViewById<FloatingActionButton>(R.id.fab_my_location).setOnClickListener {
            getDeviceLocation()
        }
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Configurar o mapa
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true
        
        // Verificar permissões de localização
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
            getDeviceLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }

        // Adicionar marcadores para estações de aluguer
        addRentalStationsToMap()
    }

    @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun getDeviceLocation() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        val currentLatLng = LatLng(it.latitude, it.longitude)
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                    } ?: run {
                        // Se a localização não estiver disponível, centralizar em Lisboa
                        val lisbonLatLng = LatLng(38.7223, -9.1393)
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbonLatLng, 12f))
                    }
                }
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Erro ao obter localização", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addRentalStationsToMap() {
        Log.d("MapActivity", "Adicionando ${rentalStations.size} estações ao mapa")
        // Adicionar marcadores apenas para as estações de aluguer
        rentalStations.forEach { station ->
            val position = LatLng(station.latitude, station.longitude)
            Log.d("MapActivity", "Estação: ${station.name}, Posição: ${station.latitude}, ${station.longitude}, Guarda-chuvas: ${station.availableUmbrellas}/${station.totalCapacity}")
            
            // Adicionar marcador para a estação
            map.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(station.name)
                    .snippet("Disponíveis: ${station.availableUmbrellas}/${station.totalCapacity}")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_rental_station))
            )
        }
        Log.d("MapActivity", "Todas as estações foram adicionadas ao mapa")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                    map.isMyLocationEnabled = true
                    getDeviceLocation()
                }
            } else {
                Toast.makeText(this, "Permissão de localização negada", Toast.LENGTH_SHORT).show()
                // Centralizar em Lisboa como fallback
                val lisbonLatLng = LatLng(38.7223, -9.1393)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lisbonLatLng, 12f))
            }
        }
    }
}