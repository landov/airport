package hu.landov.airport.common.domain.wind

//TODO overengineered, not needed
class WindStateException : Exception{
    constructor(message: String) : super (message)
    constructor(message: String, cause : Throwable) : super (message,cause)
}
