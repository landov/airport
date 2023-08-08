package hu.landov.airport.common.data

import androidx.room.TypeConverter
import kotlin.streams.toList

class Converters {

    @TypeConverter
    fun doublesToString(listOfDoubles : List<Double>) : String{
        return listOfDoubles.joinToString(";")
    }

    @TypeConverter
    fun stringTODoubles(string: String) : List<Double>{
        val array = string.split(";")
        return array.stream().map{s -> s.toDouble()}.toList()
    }

    @TypeConverter
    fun floatsToString(listOfFloats : List<Float>) : String{
        return listOfFloats.joinToString(";")
    }

    @TypeConverter
    fun stringToFloats(string: String) : List<Float>{
        val array = string.split(";")
        return array.stream().map{s -> s.toFloat()}.toList()
    }

    @TypeConverter
    fun stringsToString(strings : List<String>) : String{
        return strings.joinToString("\n")
    }

    @TypeConverter
    fun stringToString(string: String) : List<String>{
        return string.split("\n").toList()
    }

}