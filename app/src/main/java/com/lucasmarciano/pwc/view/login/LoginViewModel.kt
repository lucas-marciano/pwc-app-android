package com.lucasmarciano.pwc.view.login

import android.content.Context
import android.location.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.lucasmarciano.pwc.utils.Logger
import com.lucasmarciano.pwc.utils.prefs
import java.util.*

/**
 * View model for the login fragment
 *
 * @project pwc
 * @create_at 2019-11-18
 * @author lucasmarciano
 */
class LoginViewModel(private val context: Context) : ViewModel() {
    val TAG = Logger.tag

    private val emailHardCode = "abacomm@abacomm.com"
    private val passwordHardCode = "1234"

    /**
     * Method to make it the login.
     *
     * @param email String
     * @param password String
     * @return Boolean
     */
    fun login(email: String?, password: String?): Boolean {
        if (Logger.DEBUG) Log.d(TAG, "login")
        return email == emailHardCode && password == passwordHardCode
    }

    /**
     * Method to check if the connection is available.
     *
     * @return Boolean
     */
    fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network: NetworkInfo? = cm.activeNetworkInfo
        return network?.isConnected == true
    }

    /**
     * Method to get the address and insert in the SharedPreference
     */
    fun getCurrentLocation() {
        if (Logger.DEBUG) Log.d(TAG, "getCurrentLocation")

        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location) {
                val addresses: List<Address>
                val geoCoder = Geocoder(context, Locale.getDefault())
                addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                prefs.streetName = addresses[0].getAddressLine(0)
                prefs.cityName = addresses[0].locality
                prefs.stateName = addresses[0].adminArea
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
            }
        }

        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            0f,
            locationListener
        )
    }
}
