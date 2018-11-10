package com.example.company.foodtruck

import java.util.HashMap
import com.google.android.gms.maps.model.LatLng

class Location {

    private val previousLocations: HashMap<String, LatLng> = HashMap()

    constructor() {
        previousLocations.put("Dupont Circle", LatLng(38.908125, -77.043180))
        previousLocations.put("East Village", LatLng(40.727260, -73.982660))
        previousLocations.put("South Beach", LatLng(25.782612, -80.134079))
        previousLocations.put("Westwood", LatLng(34.066631, -118.439568))
    }

    fun fetchCurrentLocation(): LatLng {
        // TODO: Use API call
        // addLocation()
        return LatLng(0.0, 0.0)
    }

    fun fetchPreviousLocation(location: String): LatLng {
        if (previousLocations.contains(location)) {
            return previousLocations.get(location) as LatLng
        }
        return LatLng(0.0, 0.0)
    }

    fun addLocation(location: String, lat: Double, lng: Double) {
        previousLocations.put(location, LatLng(lat, lng))
    }

    fun fetchMoveTime(): String {
        // TODO: Use API call
        return "2 hours"
    }
}
