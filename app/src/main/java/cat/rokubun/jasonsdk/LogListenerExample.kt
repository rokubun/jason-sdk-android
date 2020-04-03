package cat.rokubun.jasonsdk

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cat.rokubun.sdk.LogListener
import cat.rokubun.sdk.ProcessError
import cat.rokubun.sdk.ProcessLog
import cat.rokubun.sdk.ProcessResult
import cat.rokubun.sdk.repository.remote.dto.ResultsResponse

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