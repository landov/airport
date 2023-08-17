package hu.landov.airport.airportdetails

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.landov.airport.common.domain.location.LocationState
import hu.landov.airport.common.domain.wind.WindState

@BindingAdapter("windStateSpeed")
fun TextView.setWindStateSpeed(windState: WindState?) {
    windState?.let {
        setText(
            String.format("%.0f", windState?.speed)
        )
    }
}

@BindingAdapter("windStateDirection")
fun TextView.setWindStateDirection(windState: WindState?) {
    windState?.let {
        setText(
            String.format("%.0f\u00b0", windState?.direction)
        )
    }
}

@BindingAdapter("locationStateAltitude")
fun TextView.setLocationStateAltitude(locationState: LocationState?) {
    locationState?.let {
        setText(
            String.format("%.0f", locationState.altitude)
        )
    }
}

@BindingAdapter("locationStateSpeed")
fun TextView.setLocationStateSpeed(locationState: LocationState?) {
    locationState?.let {
        setText(
            String.format("%.0f", locationState.speed)
        )
    }
}

@BindingAdapter("locationStateDistance")
fun TextView.setLocationStateDistance(locationState: LocationState?) {
    locationState?.let {
        setText(
            if (locationState.distance > 10000)
                String.format("%.1f", locationState.distance / 10000)
            else
                String.format("%.0f", locationState.distance)
        )
    }
}

@BindingAdapter("locationStateDistanceUnit")
fun TextView.setLocationStateDistanceUnit(locationState: LocationState?) {
    locationState?.let {
        setText(
            if (locationState.distance > 10000)
                "km"
            else
                "m"
        )
    }
}

@BindingAdapter("locationStateDirection")
fun TextView.setLocationStateDirection(locationState: LocationState?) {
    locationState?.let {
        setText(
            String.format("%.0f\u00b0", locationState.direction)
        )
    }
}