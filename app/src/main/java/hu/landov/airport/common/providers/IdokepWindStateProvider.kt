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
import okhttp3.OkHttpClient
import java.net.MalformedURLException
import java.net.URI
import java.security.cert.X509Certificate
import java.util.*

import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.*

const val SOCKET_EVENT_W = "w"

@Singleton
class IdokepWindStateProvider @Inject constructor() : WindStateProvider {

    private val TAG = "IdokepWindStateProvider"
    private var airport: Airport? = null
    private val windStateLiveData = MutableLiveData<WindState>()
    private var socket: Socket? = null

    override fun getWindState(airport: Airport): LiveData<WindState> {
        this.airport = airport
        start()
        return windStateLiveData
    }

    override fun start() {
        airport?.let { airport ->

            if (airport.windLink.isNotBlank()) {
                val uri = URI.create("https:" + airport.windLink)
                Log.d(TAG, uri.toString())
                try {
                    val options = getSocketOptions()
                    socket = IO.socket(uri, options)

                    socket?.on(SOCKET_EVENT_W) { args ->
                        val argString = args[0].toString()
                        val strings = argString.substring(1, argString.length - 1).split(",")
                        //TODO check data integrity
                        val windState = WindState(
                            strings[0].toDouble(),
                            strings[1].toDouble()
                        )
                        Log.d(TAG, "recived: $windState $socket")
                        windStateLiveData.postValue(windState)
                    }

                    socket?.on(Manager.EVENT_CONNECT_ERROR) {
                        Log.e(TAG, "Connection error: " + Arrays.toString(it))
                    }
                    socket?.on(Manager.EVENT_ERROR) {
                        Log.e(TAG, "Error: " + Arrays.toString(it))
                    }
                    socket?.on(Manager.EVENT_CLOSE) {
                        socket?.off()
                        Log.d(TAG, "Closing $socket")
                    }
                    socket?.on(Manager.EVENT_RECONNECT) {
                        Log.d(TAG, "Reconnecting $socket ---------------------------->>>>")
                    }
                    socket?.connect()
                } catch (e: MalformedURLException) {
                    //TODO handle
                    throw WindStateException("Mailformed url:${uri.toString()}", e)
                }
            }
        }
    }

    override fun stop() {
        socket?.close()
    }

    //TODO : Danger!!!
    //idokep.hu sometimes have invalid certification date
    private fun getSocketOptions(): IO.Options {
        val hostnameVerifier: HostnameVerifier = object : HostnameVerifier {
            override fun verify(hostname: String?, sslSession: SSLSession?): Boolean {
                return true
            }
        }

        val trustManager: X509TrustManager = object : X509TrustManager {

            override fun checkClientTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
                // not implemented
            }

            override fun checkServerTrusted(arg0: Array<X509Certificate?>?, arg1: String?) {
                // not implemented
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf<X509Certificate>()
            }
        }

        val sslContext: SSLContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf<TrustManager>(trustManager), null)

        val okHttpClient = OkHttpClient.Builder()
            .hostnameVerifier(hostnameVerifier)
            .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
            .readTimeout(1, TimeUnit.MINUTES) // important for HTTP long-polling
            .build()

        val options = IO.Options()
        options.callFactory = okHttpClient
        options.webSocketFactory = okHttpClient

        return options
    }
}