package cat.rokubun.jasonsdk

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cat.rokubun.jason.*
import cat.rokubun.jason.repository.remote.dto.SubmitProcessResult
import io.reactivex.disposables.CompositeDisposable
import java.io.File

/**
 * View Model for [JasonClient] feature.
 */
class ProcessViewModel(application: Application) : AndroidViewModel(application) {


    var jasonClient: JasonClient? = null
    var submitLiveData: MutableLiveData<SubmitProcessResult> = MutableLiveData()
    var retryLiveData: MutableLiveData<SubmitProcessResult> = MutableLiveData()
    var userLiveData: MutableLiveData<User> = MutableLiveData()
    var processResultLivedata :MutableLiveData<ProcessStatus> = MutableLiveData()
    var processInfoLiveData: MutableLiveData<List<ProcessInfo>> = MutableLiveData()
    private var compositeDisposable: CompositeDisposable? = null


    init {
        jasonClient = JasonClient.getInstance(application.applicationContext)
        compositeDisposable = CompositeDisposable()

    }


    fun submitProcess(label: String, type: String, roverFile: File, baseFile: File, location: Location) {
        compositeDisposable!!.add(jasonClient?.submitProcess(label, type, roverFile, baseFile, location)?.toObservable()?.subscribe({
            submitLiveData.postValue(it)
        },
            { Throwable(it.message) })!!)
    }

    fun submitProcess(label: String, type: String, roverFile: File) {
      compositeDisposable!!.add(
          jasonClient?.submitProcess(label, type, roverFile)?.toObservable()?.subscribe({
              submitLiveData.postValue(it)
          },
              { Throwable(it.message) })!!
      )
    }

    fun retryProcess(id: Int) {
        compositeDisposable!!.add(jasonClient?.retryProcess(id)?.toObservable()?.
            subscribe({
                Log.d("DEBUG", "retry3 ");

                retryLiveData.postValue(it)
            },
                { Throwable(it.message) })!!
        )
    }

    fun loginToJason(email: String, password: String){
        compositeDisposable!!.add(jasonClient?.login(email, password)?.
            subscribe(
                    { userLiveData.postValue(it)},
                    { error -> Throwable(error.localizedMessage)})!!)
    }

    fun getProcessStatus(processId: String, maxTimeoutMillis: Long){
        compositeDisposable!!.add(jasonClient!!.getProcessStatus(processId.toInt(), maxTimeoutMillis)
            .subscribe({processResultLivedata.postValue(it)},
                {error -> Throwable(error.localizedMessage)})!!)
    }

    fun getProcesses(token: String){
        compositeDisposable!!.add(jasonClient!!.getProcesses(token)
            .subscribe({ processInfoLiveData.postValue(it) },{ error -> Throwable(error.localizedMessage)})
        )
    }

}
