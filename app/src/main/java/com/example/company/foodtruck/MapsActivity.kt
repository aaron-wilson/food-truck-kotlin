package com.example.company.foodtruck

import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var marker: Marker
    private lateinit var text: TextView

    private val location: Location = Location()
    // TODO: Remove demo variables
    private val demoLocations: Array<String> = arrayOf("Dupont Circle", "East Village",
            "South Beach", "Westwood")
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        text = findViewById(R.id.text) as TextView
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val defaultLatLng = LatLng(0.0, 0.0)
        marker = mMap.addMarker(MarkerOptions().position(defaultLatLng).title("Food Truck Location"))
        startUpdateTimer()
    }

    private fun updateLocation() {
        // Add a marker and move the camera
        // TODO: Use location.fetchCurrentLocation()
        val newLatLng = location.fetchPreviousLocation(demoLocations[count])
        marker.position = newLatLng
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLatLng))
        if (count >= demoLocations.size - 1) {
            count = 0
        } else {
            count++
        }
    }

    private fun updateMoveTime() {
        val moveTime = location.fetchMoveTime()
        text.text = "Food Truck will move to next location in ${moveTime}"
    }

    private fun startUpdateTimer() {
        // TODO: Increase interval after API call implemented
        val timer = Timer("schedule", true)
        timer.scheduleAtFixedRate(0, 30 * 1000) {
            /*
            Before SAM Conversion
            SAM (Simple Abstract Method)

            import java.lang.Runnable

            runOnUiThread(Runnable {
                updateLocation()
                updateMoveTime()
            })
            */

            // After SAM Conversion
            runOnUiThread {
                updateLocation()
                updateMoveTime()
            }
        }
    }
}
