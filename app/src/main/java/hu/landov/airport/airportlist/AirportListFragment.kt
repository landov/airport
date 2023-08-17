package hu.landov.airport.airportlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.databinding.FragmentAirportListBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AirportListFragment : Fragment() {

    private lateinit var binding: FragmentAirportListBinding
    private val viewModel: AirportListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAirportListBinding.inflate(layoutInflater)
        return binding.root
    }

    //TODO move setups to functions
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            AirportListAdapter(mutableListOf()) { airport -> navigateToDetails(airport) }
        binding.airportListRecycler.adapter = adapter
        lifecycleScope.launch {
            viewModel.airports.collect { airports ->
                adapter.setAirports(airports)
            }
        }
    }

    private fun navigateToDetails(airport: Airport) {
        Log.d("LISTFRAGEMNT", "${airport.code} clicked!")
        val action =
            AirportListFragmentDirections.actionAirportListFragmentToAirportDetailsFragment(
                airport
            )
        try {
            findNavController().navigate(action)
        } catch (e: Exception) {
            //TODO Found out why
            e.printStackTrace()
        }
    }

}