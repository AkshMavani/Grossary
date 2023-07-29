package com.example.groceriesapp.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

class Network(private val context: Context): LiveData<Boolean>() {
    private val connectivityManager: ConnectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var networkConnectionCallback: ConnectivityManager.NetworkCallback
    @SuppressLint("ObsoleteSdkInt")
    override fun onActive() {
        super.onActive()
        updateConnection()
        when{
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.N->{
                connectivityManager.registerDefaultNetworkCallback(connextionmanagercallback())
            }
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP->{
                lolipop()
            }else->{
            context.registerReceiver(networkReciver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            connectivityManager.unregisterNetworkCallback(connextionmanagercallback())
        }else{
            context.unregisterReceiver(networkReciver)
        }
    }


    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lolipop(){
        val requsetBuilder= NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(requsetBuilder.build(),connextionmanagercallback())
    }
    @SuppressLint("ObsoleteSdkInt")
    private fun connextionmanagercallback(): ConnectivityManager.NetworkCallback{
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            networkConnectionCallback=object : ConnectivityManager.NetworkCallback(){
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }
            return networkConnectionCallback
        }
        return networkConnectionCallback
    }
    private fun updateConnection(){
        val activeNetwork: NetworkInfo = connectivityManager.activeNetworkInfo!!
        postValue(activeNetwork.isConnected==true)
    }
    private val networkReciver=object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            updateConnection()
        }

    }
}