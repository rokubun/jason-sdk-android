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
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains("spp.kml")}?.url ?: "Url not found"
    }

    /**
     * Returns spp CSV url.
     */
    fun getSppCsvUrl(): String{
        return  listResultsResponse.find { it.name.toLowerCase()
            .contains("spp.csv") }?.url ?: "Url not found"

    }

    /**
     * Returns number of satellites url.
     */
    fun getNumSatellitesUrlPng():String{
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains( "num_satellites.png")}?.url ?: "Url not found"

    }

    /**
     * Returns precise CSV url.
     */

    fun getPreciseCsvUrl() : String{
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains( "ppp.csv") }?.url ?: "Url not found"

    }

    /**
     * Returns precise KML url
     */
    fun getpreciseKmlUrl(): String{
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains( "ppp.kml") }?.url ?: "Url not found"
    }

    /**
     * Returns if a process is static
     */
    fun isStatic(): Boolean {

        return listResultsResponse.any { !it.staticPos.isNullOrEmpty() }
    }
    /**
     * Returns static position
     */
    fun  getStaticPosition(): String {

        return listResultsResponse.lastOrNull()?.staticPos ?: "Static position not found"
    }

    /**
     * Returns processing type (SPP, PPP, RTK, PPK)
     */
    fun getProcessingType(): String {
        return  listResultsResponse.findLast { it.name.toLowerCase().contains(".kml") }!!
            .name.split('_').last().split('.')[0].toUpperCase()
    }

    /**
     * Returns sky plot url
     */
    fun getSkyPlotUrl(): String{
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains( "skyplot.png") }?.url ?: "Url not found"
    }

    /**
     * Returns zip url
     */
    fun getZipUrl(): String{
        return listResultsResponse.find{ it.name.toLowerCase()
            .contains( ".zip") }?.url ?: "Url not found"
    }
    //TODO
    private fun getNumEpochs() {

    }


}




