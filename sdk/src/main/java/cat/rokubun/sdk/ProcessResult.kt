package cat.rokubun.sdk

import cat.rokubun.sdk.repository.remote.dto.ResultsResponse

class ProcessResult(val listResultsResponse: List<ResultsResponse>){
/*
result.getNumSattelitesUrlPng()
result.getSppKmlUrl
result.getSppCsvUrl
result.getPreciseCsvUrl
result.getpreciseKmlUrl
result.isStatic
result.result.getStaticPosition
result.getProcessingType = SPP, PPP, PPK
result.getNumEpochs()
 */

    fun getSppKmlUrl(): String {
        val sppKmlUrl = listResultsResponse.filter{ it.name.contains("_position_spp.kml")}
        return sppKmlUrl.last().url
    }
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




