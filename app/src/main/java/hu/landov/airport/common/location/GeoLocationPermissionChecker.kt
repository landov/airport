package hu.landov.airport.common.location

/**
 * Abstraction which allows to manage the permission check
 */
interface GeoLocationPermissionChecker {

    /**
     * @return True if the permission has been provided
     */
    val isPermissionGiven: Boolean
}