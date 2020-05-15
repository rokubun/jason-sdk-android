package cat.rokubun.jason

import cat.rokubun.jason.repository.remote.dto.ResultsResponse

/**
 * This class returns information from the processed file
 * @param listResultsResponse list of [ResultsResponse]
 */

class ProcessResult(val listResultsResponse: List<ResultsResponse>){

    /**
     * Returns spp KMl url.
     */
    fun getSppKmlUrl(): String {
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains("spp.kml")}?.value ?: "Url not found"
    }

    /**
     * Returns spp CSV url.
     */
    fun getSppCsvUrl(): String{
        return  listResultsResponse.find { it.name!!.toLowerCase()
            .contains("spp.csv") }?.value ?: "Url not found"

    }

    /**
     * Returns number of satellites url.
     */
    fun getNumSatellitesUrlPng():String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains( "num_satellites.png")}?.value ?: "Url not found"

    }

    /**
     * Returns precise CSV url.
     */

    fun getPreciseCsvUrl() : String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains( "ppp.csv") }?.value ?: "Url not found"

    }

    /**
     * Returns precise KML url
     */
        fun getpreciseKmlUrl(): String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains( "ppp.kml") }?.value ?: "Url not found"
    }

    /**
     * Returns if a process is static
     */
    fun isStatic(): Boolean {

        return listResultsResponse.any{ it.name!!.toLowerCase()
            .contains( "static") }
    }
    /**
     * Returns static position
     */
    fun  getStaticPosition(): String {
        return listResultsResponse.find{ it.name!!.toLowerCase().contains("static")}?.value ?: "Static position not found"
    }

    /**
     * Returns processing type (SPP, PPP, RTK, PPK)
     */
    fun getProcessingType(): String {
        return  listResultsResponse.findLast { it.name!!.toLowerCase().contains(".kml") }!!
            .name!!.split('_').last().split('.')[0].toUpperCase()
    }

    /**
     * Returns sky plot url
     */
    fun getSkyPlotUrl(): String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains( "skyplot.png") }?.value ?: "Url not found"
    }

    /**
     * Returns zip url
     */
    fun getZipUrl(): String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains( ".zip") }?.value ?: "Url not found"
    }

    /**
     * Returns number of epochs
     */
    fun getNumEpochs() : String{
        return listResultsResponse.find{ it.name!!.toLowerCase()
            .contains("num_epochs")}?.value ?: "Value not found"
    }

}




