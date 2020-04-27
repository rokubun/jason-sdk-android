package cat.rokubun.jasonsdk

import android.util.Log
import cat.rokubun.jason.LogListener
import cat.rokubun.jason.ProcessError
import cat.rokubun.jason.ProcessLog
import cat.rokubun.jason.ProcessResult

class LogListenerExample: LogListener {


    override fun onLogReceived(processLogList: List<ProcessLog>) {
        Log.d("onLogReceived", processLogList.get(0).message)
    }

    override fun onFinish(processResult: ProcessResult) {
        Log.d("onFinish", processResult.getSppCsvUrl())
        Log.d("onFinish", processResult.getSppKmlUrl())
    }

    override fun onError(processError: ProcessError) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}