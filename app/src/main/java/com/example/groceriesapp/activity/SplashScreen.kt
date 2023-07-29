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
    private lateinit var binding:ActivitySplashScreenBinding
    private val REQUEST_CHECK_SETTINGS = 214
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)

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
       // getlocation()
        super.onResume()
    }


}