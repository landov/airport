package hu.landov.airport.airportlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.landov.airport.common.domain.airport.Airport
import hu.landov.airport.databinding.ItemAirportBinding

class AirportListAdapter(

    private val airports : MutableList<Airport>,
    private val listener : (Airport) -> Unit

    ) : RecyclerView.Adapter<AirportListAdapter.AirportViewHolder>() {

    class AirportViewHolder(private val binding : ItemAirportBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(airport: Airport){
            binding.airport = airport
        }
    }

    fun setAirports(airportList: List<Airport>){
        this.airports.clear()
        this.airports.addAll(airportList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        val holder = AirportViewHolder(
            ItemAirportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        holder.itemView.setOnClickListener{
            listener(airports[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        holder.bind(airports[position])
    }

    override fun getItemCount(): Int = airports.size

}