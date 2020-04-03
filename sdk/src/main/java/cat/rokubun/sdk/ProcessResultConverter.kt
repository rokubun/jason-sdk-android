package cat.rokubun.sdk

import cat.rokubun.sdk.repository.remote.dto.ProcessStatusResult

object ProcessResultConverter {

    var processLogList =  mutableListOf<ProcessLog>()

    fun getProcessLogFromStatusResult(processStatusResult: ProcessStatusResult): List<ProcessLog>{

        for(result in processStatusResult.log){
            val res  = ProcessLog(result.id, result.process_id, result.level, result.message, result.created)
            processLogList.add(res)
        }

        return processLogList
    }
}