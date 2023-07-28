package com.example.primierleaguematches

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.primierleaguematches.presentation.MatchesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

val auth_token = "b15df01858d84d8ca433ae18d66e02af"
fun convertIsoToDateTime(isoTimestamp: String): Pair<String, String> {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")
    val date = sdf.parse(isoTimestamp)
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return Pair(dateFormatter.format(date!!), timeFormatter.format(date))
}

fun MutableStateFlow<MatchesState>.convertMutableToImmutable(scope: CoroutineScope): StateFlow<MatchesState> {
    return this.stateIn(
        scope,
        SharingStarted.Eagerly,
        MatchesState.Loading
    )
}

fun getTodayDate(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH)
    val date = Date()
    return formatter.format(date)
}
 fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(network) ?: return false

    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}


