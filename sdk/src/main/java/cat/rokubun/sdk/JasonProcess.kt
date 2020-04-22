package cat.rokubun.sdk

class JasonProcess {

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
