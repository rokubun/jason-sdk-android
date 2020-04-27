package cat.rokubun.jason

import cat.rokubun.jason.repository.remote.dto.ResultsResponse

/**
 * This class returns information from the processed file
 * @param listResultsResponse list of [ResultsResponse]
 */

class ProcessResult(val listResultsResponse: List<ResultsResponse>){

    /**
     * return spp KMl url
     */
    fun getSppKmlUrl(): String {
        val sppKmlUrl = listResultsResponse.filter{ it.name.contains("_position_spp.kml")}
        return sppKmlUrl.last().url
    }

    /**
     * return spp CSV URL
     */
    fun getSppCsvUrl(): String{
        val sppCsvUrl = listResultsResponse.singleOrNull { it.name.contains("_position_spp.csv") }
        return sppCsvUrl!!.url
    }


    //TODO
    fun getPreciseCsvUrl() : Double{
        return 0.0
    }
    //TODO
    fun getpreciseKmlUrl(): Double{
        return 0.0
    }
    //TODO
    fun isStatic(): Boolean{
        return false
    }
    //TODO
    fun  getStaticPosition() {

    }
    //TODO
    fun getProcessingType() {

    }
    //TODO
    fun getNumEpochs() {

    }


}




