package hu.landov.airport

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.app.ActivityCompat
import hu.landov.airport.common.di.*
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var permissionChecker: GeoLocationPermissionChecker
    lateinit var comp: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comp = application.appComp
            .activityComponentFactory()
            .create(this).apply {
                inject(this@MainActivity)
            }
        checkPermission()
    }

    private fun checkPermission() {
        if (!permissionChecker.isPermissionGiven) {
            Log.e("SPLASH", "Request premisson!")
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 0
            )
        }
    }
}

val Context.activityComp: ActivityComponent?
    get() = if (this is MainActivity) comp else null