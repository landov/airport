package hu.landov.airport.common.domain.wind

class WindStateException : Exception{
    constructor(message: String) : super (message)
    constructor(message: String, cause : Throwable) : super (message,cause)
}
