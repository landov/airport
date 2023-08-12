package hu.landov.airport.common.providers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.wind.WindState
import hu.landov.airport.common.domain.wind.WindStateException
import hu.landov.airport.common.domain.wind.WindStateProvider
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import java.net.MalformedURLException
import java.net.URI


class IdokepWindStateProvider : WindStateProvider {

    private val TAG = "IdokepWindStateProvider"
    private var airport: Airport? = null
    private val windStateLiveData = MutableLiveData<WindState>()
    private var thread : Thread? = null

    private var socket: Socket? = null

    override fun getWindState(airport: Airport): LiveData<WindState> {
        this.airport = airport
        return windStateLiveData
    }

    override fun start() {
        airport?.let {airport ->
            if (airport.windLink.isNotBlank()) {
                val uri = URI.create("https:" + airport.windLink)
                Log.d(TAG, uri.toString())
                try {
                    //TODO something with options builder cos it's leaking
                    socket = IO.socket(uri)
                    socket?.on("w") { args ->
                        thread = Thread.currentThread()
                        val argString = args[0].toString()
                        val strings = argString.substring(1, argString.length - 1).split(",")
                        //TODO check data integrity
                        val windState = WindState(
                            strings[0].toDouble(),
                            strings[1].toDouble()
                        )
                        Log.d(TAG, "recived: $windState $socket $thread")
                        windStateLiveData.postValue(windState)
                    }
                    socket?.on(Manager.EVENT_CLOSE){}
                    socket?.on(Manager.EVENT_RECONNECT){
                        Log.d(TAG,"Reconnecting $socket ---------------------------->>>>")
                    }
                    socket?.connect()
                } catch (e: MalformedURLException) {
                    //TODO handle
                    throw WindStateException("Mailformed url:${uri.toString()}")
                }
            }
        }
    }

    override fun stop() {
        Log.d(TAG,"Disconnecting $socket")
        socket?.listeners("w")?.clear()
        socket?.disconnect()
        socket?.close()


    }
}