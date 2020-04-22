package cat.rokubun.jasonsdk


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jasonsdk.utlis.FileUtils.getFileName
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.JasonProcess
import cat.rokubun.sdk.domain.Location
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class SubmitProcessActivity : AppCompatActivity() {

    @BindView(R.id.process_button)
    lateinit var processButton: Button
    @BindView(R.id.Logbutton)
    lateinit var logButton: Button
    @BindView(R.id.processEditText)
    lateinit var processNumber: EditText
    @BindView(R.id.upload_action_button)
    lateinit var uploadButton: FloatingActionButton
    @BindView(R.id.roverFileNameTextView)
    lateinit var roverFileNameTextView: TextView
    @BindView(R.id.baseFileTextView)
    lateinit var baseFileNameTextView: TextView

    private var uploadFile: File? = null
    var fileList: MutableList<File> = mutableListOf<File>()
    private val PICKFILE_RESULT_CODE: Int = 1001
    var jasonClient: JasonClient ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_process)
        jasonClient = JasonClient.getInstance(applicationContext)
        ButterKnife.bind(this)
    }


    @OnClick(R.id.process_button)
    fun processFile() {
        when {
            fileList.isNotEmpty() -> {
                val location: Location = Location(41.418524750001879,1.986951633036511,319.924932730384171)
                jasonClient!!.submitProcess("GNSS", fileList.get(0), fileList.get(1), location)
            }
            uploadFile != null -> jasonClient!!.submitProcess("GNSS", uploadFile!!)

            else -> Toast.makeText(baseContext, "Please choose a file to process", Toast.LENGTH_SHORT).show()
        }
    }

    @OnClick(R.id.upload_action_button)
    fun uploadFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
            .addCategory(Intent.CATEGORY_OPENABLE)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        startActivityForResult(Intent.createChooser(intent,"Files"), PICKFILE_RESULT_CODE)

    }

    @OnClick(R.id.Logbutton)
    fun getLogs() {
            val number: Int = processNumber.text.toString().toInt()
            try{
                jasonClient?.registerLogListener(number)
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe ({ it ->
                        if(!it.processLog?.last()?.message.isNullOrEmpty()){
                            Log.d("CSV: ", it.processLog?.last()?.message)
                        }
                    }, Throwable::printStackTrace)

            }catch (e: Exception){
                Log.e("Error: ",  "", e)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PICKFILE_RESULT_CODE -> if(resultCode == Activity.RESULT_OK){
                if(data?.clipData != null){
                    for ( i in 0 until data.clipData!!.itemCount) {
                        val fileUri = data.clipData?.getItemAt(i)?.uri!!
                        val parcelFileDescriptor =
                            baseContext.contentResolver.openFileDescriptor(fileUri, "r", null)
                        createTmpFile(parcelFileDescriptor, fileUri)
                        fileList.add(uploadFile!!)
                    }
                    roverFileNameTextView.text =  getFileName(fileList.get(0).toUri())
                    baseFileNameTextView.text = getFileName(fileList.get(1).toUri())
                }else{
                    val parcelFileDescriptor =
                        baseContext.contentResolver.openFileDescriptor(data?.data!!, "r", null)
                    createTmpFile(parcelFileDescriptor, data.data!!)
                }
            }
        }

    }
    fun Intent.getData(key: String): String {
        return extras?.getString(key) ?: "intent is null"
    }
    private fun SubmitProcessActivity.createTmpFile(
        parcelFileDescriptor: ParcelFileDescriptor?,
        fileUri: Uri
    ) {
        parcelFileDescriptor?.let {
            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            uploadFile = File(baseContext.cacheDir, getFileName(fileUri))
            val outputStream = FileOutputStream(uploadFile)
            IOUtils.copy(inputStream, outputStream)
        }
    }
}




