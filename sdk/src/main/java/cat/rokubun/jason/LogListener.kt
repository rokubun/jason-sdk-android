package cat.rokubun.jason

interface LogListener {

    fun onLogReceived(processLog: List<ProcessLog>)
    fun onFinish(processResult: ProcessResult)
    fun onError(processError: ProcessError)
}