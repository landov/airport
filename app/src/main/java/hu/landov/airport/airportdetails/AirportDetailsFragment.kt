package hu.landov.airport.airportdetails

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import hu.landov.airport.common.di.LOCATION_MANAGER

import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.databinding.FragmentAirportDetailsBinding
import hu.landov.airport.lookup


class AirportDetailsFragment : Fragment() {
    private lateinit var viewModel: AirportDetailsViewModel
    private lateinit var binding: FragmentAirportDetailsBinding
    private val args: AirportDetailsFragmentArgs by navArgs()
    private lateinit var airport: Airport
    //private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        airport = args.airport
        val locationManager: LocationManager =
            (activity as AppCompatActivity).lookup(LOCATION_MANAGER)
        //locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        viewModel =
            ViewModelProvider(
                this, AirportDetailsViewModelFactory(airport, locationManager)
            ).get(
                AirportDetailsViewModel::class.java
            )
        Log.d("DETAILS", airport.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirportDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onStart()
    }

    override fun onPause() {
        viewModel.onStop()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }


}