package cat.rokubun.jason

import cat.rokubun.jason.repository.remote.dto.ProcessStatusResult

object ProcessResultConverter {

    private var processLogList =  mutableListOf<ProcessLog>()

    /**
     * Transforms response values from [StatusResult] to a list
     *
     */
    fun getProcessLogFromStatusResult(processStatusResult: ProcessStatusResult): List<ProcessLog>{

        for(result in processStatusResult.log){
            val res  = ProcessLog(result.id, result.process_id, result.level, result.message, result.created)
            processLogList.add(res)
        }

        return processLogList
    }
}