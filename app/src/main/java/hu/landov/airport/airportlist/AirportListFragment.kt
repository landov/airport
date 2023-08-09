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
import hu.landov.airport.common.data.AirportEntity
import hu.landov.airport.common.domain.Airport
import hu.landov.airport.databinding.FragmentAirportListBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AirportListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
    //TODO  viewLifecycleOwner.lifecycleScope + viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
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