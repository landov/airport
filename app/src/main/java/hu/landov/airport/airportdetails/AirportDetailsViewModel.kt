package hu.landov.airport.airportdetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport
import hu.landov.airport.common.domain.MPSZEL_URL
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URI
import java.util.Timer
import kotlin.concurrent.timer

const val TAG = "AIRPORTDETAILSVIEWMODEL"

class AirportDetailsViewModel(val airport: Airport) : ViewModel() {

    private var socket: Socket? = null

    val windSpeed = MutableLiveData(" - ")
    val windDirection = MutableLiveData(" - ")

    init {
        Log.d(TAG, "is live with $airport")
        Log.d(TAG, "is live with ${airport.windLink}")
        //TODO refractor?
        if (airport.windLink.isNotBlank()) {
            val uri = URI.create("https:" + airport.windLink)
            Log.d(TAG, uri.toString())
            try {
                socket = IO.socket(uri)
                socket?.on("w") { args ->
                    val argString = args[0].toString()
                    val strings = argString.substring(1,argString.length-1).split(",")
                    windSpeed.postValue(strings[0]+"km/h")
                    windDirection.postValue(strings[1]+"\u00B0")
                }
                socket?.connect()
            } catch (e: MalformedURLException) {
                //TODO handle
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
      //  timer.cancel()
        socket?.close()
        super.onCleared()
    }
}

var Socket.windSpeed: MutableLiveData<String>
    get() = MutableLiveData<String>(" hH")
    set(value) = TODO()


class AirportDetailsViewModelFactory(private val airportEntity: Airport) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirportDetailsViewModel::class.java)) {
            return AirportDetailsViewModel(airportEntity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}