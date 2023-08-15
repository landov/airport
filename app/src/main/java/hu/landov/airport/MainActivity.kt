package hu.landov.airport

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import hu.landov.airport.common.di.*
import hu.landov.airport.common.location.GeoLocationPermissionChecker
import hu.landov.airport.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var permissionChecker: GeoLocationPermissionChecker
    lateinit var comp: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comp = DaggerAppComponent
            .builder()
            .providerModule(ProviderModule(this))
            .build().apply {
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

val Context.comp: AppComponent?
    get() = if (this is MainActivity) comp else null