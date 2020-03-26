package cat.rokubun.jasonsdk

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jasonsdk.utlis.FileUtils.createTmpFile
import cat.rokubun.jasonsdk.utlis.FileUtils.getFileName
import cat.rokubun.sdk.JasonClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File



class SubmitProcessActivity : AppCompatActivity() {

    @BindView(R.id.process_button)
    lateinit var processButton: Button
    @BindView(R.id.upload_action_button)
    lateinit var uploadButton: FloatingActionButton
    @BindView(R.id.fileInfoTextView)
    lateinit var fileInfo: TextView
    @BindView(R.id.roverFileNameTextView)
    lateinit var roverFileNameTextView: TextView

    private var roverFile: File? = null
    val builder = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_process)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.process_button)
    fun processFile() {
        if (roverFile!!.equals("") || roverFile == null) {
            Toast.makeText(baseContext, "Please choose a file to process", Toast.LENGTH_SHORT)
                .show()
        } else {
            JasonClient.submitProcess("GNSS", roverFile!!)
            Toast.makeText(baseContext, "Processing", Toast.LENGTH_SHORT).show()
        }
    }

    @OnClick(R.id.upload_action_button)
    fun uploadFile() {
        val intent = Intent()
            .setType("text/plain")
            .setAction(Intent.ACTION_GET_CONTENT)
            .putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file

            if (selectedFile != null) {
                contentResolver.openInputStream(selectedFile)?.bufferedReader()?.forEachLine {
                    roverFile = createTmpFile(it, getFileName(selectedFile)!!)
                    //FIXME show HEADER of FIlE
                    fileInfo.text = it
                }
            }

            roverFileNameTextView.text = getFileName(selectedFile)
        }
    }
}

