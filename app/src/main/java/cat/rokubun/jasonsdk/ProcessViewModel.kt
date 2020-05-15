package cat.rokubun.jasonsdk

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import cat.rokubun.jason.JasonClient
import cat.rokubun.jason.Location
import cat.rokubun.jason.ProcessStatus
import cat.rokubun.jason.User
import cat.rokubun.jason.repository.remote.dto.SubmitProcessResult
import io.reactivex.disposables.CompositeDisposable
import java.io.File

/**
 * View Model for [JasonClient] feature.
 */
class ProcessViewModel(application: Application) : AndroidViewModel(application) {


    var jasonClient: JasonClient? = null
    var submitLiveData: MutableLiveData<SubmitProcessResult> = MutableLiveData()
    var userLiveData: MutableLiveData<User> = MutableLiveData()
    var processResultLivedata :MutableLiveData<ProcessStatus> = MutableLiveData()
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

    fun loginToJason(email: String, password: String){
        compositeDisposable!!.add(jasonClient?.login(email, password)
            ?.subscribe(
                    { userLiveData.postValue(it)},
                    { error -> Throwable(error.localizedMessage)})!!)
    }

    fun getProcessStatus(processId: String, maxTimeoutMillis: Long){
        compositeDisposable!!.add(jasonClient!!.getProcessStatus(processId.toInt(), maxTimeoutMillis)
            .subscribe({processResultLivedata.postValue(it)},
                {error -> Throwable(error.localizedMessage)})!!)
    }

}
