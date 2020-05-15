package cat.rokubun.jasonsdk


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jason.Location
import cat.rokubun.jasonsdk.utlis.FileUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Submit Process Fragment
 */
class SubmitProcessFragment : Fragment() {

    @BindView(R.id.process_button)
    lateinit var processButton: Button
    @BindView(R.id.selectFileButton)
    lateinit var uploadButton: Button
    @BindView(R.id.roverFileNameTextView)
    lateinit var roverFileNameTextView: TextView
    @BindView(R.id.baseFileTextView)
    lateinit var baseFileNameTextView: TextView
    @BindView(R.id.baseTitle)
    lateinit var baseTile: TextView

    private var uploadFile: File? = null
    var fileList: MutableList<File> = mutableListOf<File>()
    private val PICKFILE_RESULT_CODE: Int = 1001
    var fileUtils: FileUtils? = null
    private val processViewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fileUtils  = FileUtils(this.context!!)
        val view: View = inflater.inflate(R.layout.fragment_submit_process, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.process_button)
    fun processFile() {
         when {
             fileList.isNotEmpty() -> {
                 val location: Location =
                     Location(
                         41.418524750001879,
                         1.986951633036511,
                         319.924932730384171
                     )
                 processViewModel.submitProcess("Label","GNSS", fileList.get(0), fileList.get(1), location)
             }
             uploadFile != null -> processViewModel.submitProcess("Label","GNSS", uploadFile!!)
             else -> Toast.makeText(context, "Please choose a file to process", Toast.LENGTH_SHORT).show()
         }
         submitVerification()
    }

    private fun submitVerification() {
        processViewModel.submitLiveData.observe(this, Observer {
            if (it.message.equals("success")) {
                val action = SubmitProcessFragmentDirections.actionSubmitToLogs(it.id!!)
                Navigation.findNavController(this.view!!).navigate(action)
            } else {
                Toast.makeText(context, "Somenthing went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @OnClick(R.id.selectFileButton)
    fun uploadFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(Intent.createChooser(intent,"Files"), PICKFILE_RESULT_CODE)
    }

    fun getProcesses(token: String) {
        processViewModel.getProcesses(token)
        processViewModel.processInfoLiveData.observe(this, Observer {
                for (pro in it) {
                Log.d("id", pro.id.toString())
                Log.d("process", pro.sourceFile.split('/').last())
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            PICKFILE_RESULT_CODE -> if(resultCode == Activity.RESULT_OK){
                if(data?.clipData != null){
                    for ( i in 0 until data.clipData!!.itemCount) {
                        val fileUri = data.clipData?.getItemAt(i)?.uri!!
                        val parcelFileDescriptor =
                            context!!.contentResolver.openFileDescriptor(fileUri, "r", null)
                        createTmpFile(parcelFileDescriptor, fileUri)
                        fileList.add(uploadFile!!)
                    }
                    setFileNameTextView()

                }else{
                    roverFileNameTextView.text = fileUtils?.getFileName(data!!.data)
                    val parcelFileDescriptor =
                        context!!.contentResolver.openFileDescriptor(data?.data!!, "r", null)
                    createTmpFile(parcelFileDescriptor, data.data!!)
                }
            }
        }

    }

    private fun setFileNameTextView() {
        roverFileNameTextView.text = fileUtils?.getFileName(fileList.get(1).toUri())
        baseFileNameTextView.text = fileUtils?.getFileName(fileList.get(0).toUri())
        baseFileNameTextView.visibility = View.VISIBLE
        baseTile.visibility = View.VISIBLE
    }

    private fun SubmitProcessFragment.createTmpFile(
        parcelFileDescriptor: ParcelFileDescriptor?,
        fileUri: Uri
    ) {
        parcelFileDescriptor?.let {
            val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
            uploadFile = File(context?.cacheDir, fileUtils?.getFileName(fileUri))
            val outputStream = FileOutputStream(uploadFile)
            IOUtils.copy(inputStream, outputStream)
        }
    }

}






