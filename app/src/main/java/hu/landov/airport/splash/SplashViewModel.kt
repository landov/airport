package hu.landov.airport.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.landov.airport.AirportApplication
import hu.landov.airport.common.domain.airport.Airport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.util.*

//TODO Inject Repo
class SplashViewModel(val app: Application) : AndroidViewModel(app) {

    val repository = (app as AirportApplication).getAirportRepository()
    private var _proceed = MutableLiveData<Boolean>(false)
    val proceed : LiveData<Boolean>
    get() = _proceed

    init {
        loadDatabase()
    }

    fun loadDatabase(){
        CoroutineScope(Dispatchers.IO).launch {

            val ist: InputStream = app.resources.openRawResource(
                app.resources.getIdentifier(
                    "airports",
                    "raw",
                    app.packageName
                )
            )
            val scanner = Scanner(ist)
            while(scanner.hasNextLine()){
                val line = scanner.nextLine()
                val airport = Airport.fromString(line)
                repository.addAirport(airport)
                Log.d("SPLASH", "$airport");
            }

            _proceed.postValue(true)
        }
    }


}