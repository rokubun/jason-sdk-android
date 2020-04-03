package cat.rokubun.sdk

interface LogListener {

    fun onLogReceived(processLog: List<ProcessLog>)
    fun onFinish(processResult: ProcessResult)
    fun onError(processError: ProcessError)
}