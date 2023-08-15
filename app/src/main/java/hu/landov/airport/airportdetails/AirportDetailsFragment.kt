package hu.landov.airport.airportdetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import hu.landov.airport.activityComp
import hu.landov.airport.appComp

import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.common.domain.location.LocationStateProvider
import hu.landov.airport.common.domain.wind.WindStateProvider
import hu.landov.airport.databinding.FragmentAirportDetailsBinding
import javax.inject.Inject


class AirportDetailsFragment : Fragment() {
    private lateinit var viewModel: AirportDetailsViewModel
    private lateinit var binding: FragmentAirportDetailsBinding
    private val args: AirportDetailsFragmentArgs by navArgs()
    private lateinit var airport: Airport
    @Inject
    lateinit var windStateProvider: WindStateProvider
    @Inject
    lateinit var locationStateProvider: LocationStateProvider

    override fun onAttach(context: Context) {
        context.activityComp
            ?.fragmentComponent()
            ?.inject(this)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        airport = args.airport
        viewModel =
            ViewModelProvider(
                this, AirportDetailsViewModelFactory(airport, locationStateProvider, windStateProvider)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }


}