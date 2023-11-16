package com.omongole.fred.composenewsapp.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI

object CoreUtility {

    fun isInternetConnected( context: Context ) :  Boolean {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            actNw.hasTransport(TRANSPORT_WIFI) -> true
            actNw.hasTransport(TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

}