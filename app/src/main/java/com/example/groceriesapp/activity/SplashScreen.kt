package com.example.groceriesapp.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.groceriesapp.databinding.ActivitySplashScreenBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import java.util.Locale


@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var binding:ActivitySplashScreenBinding
    private val REQUEST_CHECK_SETTINGS = 214
    private var REQUEST_CODE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.btnStart.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun enablegps() {

        val mLocationRequest = com.google.android.gms.location.LocationRequest.create()
            .setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(2000)
            .setFastestInterval(1000)

        val settingsBuilder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
        settingsBuilder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(this).checkLocationSettings(settingsBuilder.build())
        result.addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)
            } catch (ex: ApiException) {

                when (ex.statusCode) {

                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {

                        Toast.makeText(this,"GPS IS OFF",Toast.LENGTH_SHORT).show()

                        // Show the dialog by calling startResolutionForResult(), and check the result
                        // in onActivityResult().
                        val resolvableApiException = ex as ResolvableApiException
                        resolvableApiException.startResolutionForResult(this,REQUEST_CHECK_SETTINGS
                        )
                    } catch (e: IntentSender.SendIntentException) {
                        Toast.makeText(this,"PendingIntent unable to execute request.",Toast.LENGTH_SHORT).show()

                    }

                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                        Toast.makeText(
                            this,
                            "Something is wrong in your GPS",
                            Toast.LENGTH_SHORT
                        ).show()

                    }


                }
            }



        }

    }

//    fun statusCheck() {
//        val manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            buildAlertMessageNoGps()
//        }
//    }
//
//    private fun buildAlertMessageNoGps() {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
//            .setCancelable(false)
//            .setPositiveButton("Yes", DialogInterface.OnClickListener {
//                    dialog, id -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//            })
//            .setNegativeButton("No",
//                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
//        val alert: AlertDialog = builder.create()
//        alert.show()
//    }

    override fun onResume() {
      //  statusCheck()
    enablegps()
        getlocation()
        super.onResume()
    }
    private fun getlocation(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mFusedLocationClient?.lastLocation?.addOnSuccessListener { task->
                Log.e("TAG", "task: $task")
                if (task==null) {
                    requestNewLocationData()
                }else{
                    try {
                        val geocoder= Geocoder(this, Locale.getDefault())
                        val add: List<Address>? = geocoder.getFromLocation(task.latitude, task.longitude, 1)
                        //binding.latitudeTextView.text= add?.get(0)?.latitude.toString()
                       // binding.longitTextView.text= add?.get(0)?.longitude.toString()
                        binding.txtlocation.text= add!![0].getAddressLine(0)
                       // binding.CityTextView.text= add[0].locality
                       // binding.CountryTextView.text= add[0].countryName
                        val locality: String = add.get(0).locality
                        val subLocality: String = add.get(0).getSubLocality()
                        val state: String = add.get(0).getAdminArea()
                        val country: String = add.get(0).getCountryName()
                        val postalCode: String = add.get(0).getPostalCode()
                        val knownName: String = add.get(0).getFeatureName()
                        Log.e("TAG", "onLocationResult:${locality} ")
                        Log.e("TAG", "onLocationResult:${subLocality} ")
                        Log.e("TAG", "onLocationResult:${state} ")
                        Log.e("TAG", "onLocationResult:${country} ")
                        Log.e("TAG", "onLocationResult:${postalCode} ")
                        Log.e("TAG", "onLocationResult:${knownName} ")
                    }catch (e:Exception){
                        Log.e("TAG", "getlocation: $e")
                    }
                }

            }
        }else{
            askpermissin()
        }
    }
    private fun askpermissin(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
    }
    private fun requestNewLocationData() {
        val mLocationRequest = com.google.android.gms.location.LocationRequest()
        mLocationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback: LocationCallback = object  : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val mLastLocation = p0.lastLocation
            val geocoder= Geocoder(applicationContext, Locale.getDefault())
            val add: List<Address>? = mLastLocation?.let { geocoder.getFromLocation(it.latitude, mLastLocation.longitude, 1) }
           // binding.txtlocation.text= add?.get(0)?.latitude.toString()
           // binding.longitTextView.text= add?.get(0)?.longitude.toString()
           binding.txtlocation.text= add!![0].locality
            val address: String = add.get(0)
                .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            val locality: String = add.get(0).getLocality()
            val subLocality: String = add.get(0).getSubLocality()
            val state: String = add.get(0).getAdminArea()
            val country: String = add.get(0).getCountryName()
            val postalCode: String = add.get(0).getPostalCode()
            val knownName: String = add.get(0).getFeatureName()
            Log.e("TAG", "onLocationResult:${locality} ")
            Log.e("TAG", "onLocationResult:${subLocality} ")
            Log.e("TAG", "onLocationResult:${state} ")
            Log.e("TAG", "onLocationResult:${country} ")
            Log.e("TAG", "onLocationResult:${postalCode} ")
            Log.e("TAG", "onLocationResult:${knownName} ")

          //  binding.CityTextView.text= add[0].locality
          //  binding.CountryTextView.text= add[0].countryName
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getlocation()
            }else{
                Toast.makeText(this,"Please reqired permission",Toast.LENGTH_SHORT).show()
            }
        }
    }

}