package cat.rokubun.jason

/**
 * Contains the result of the processes that are emitted every periodically
 * [JasonClient.getProcessStatus] is called.
 */
class ProcessStatus {

    var processLog :List<ProcessLog> ?= null
    var processResult: ProcessResult ?= null

    constructor(processLog :List<ProcessLog>) {
        this.processLog = processLog;
    }
    constructor(processLog :List<ProcessLog>, processResult: ProcessResult){
        this.processLog = processLog;
        this.processResult = processResult
    }


}
