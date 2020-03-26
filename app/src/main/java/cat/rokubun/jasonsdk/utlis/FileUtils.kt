package cat.rokubun.jasonsdk.utlis

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import cat.rokubun.jasonsdk.SubmitProcessActivity
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object FileUtils {

    fun Context.getFileName(uri: Uri?): String?
            = when (uri?.scheme) {
        ContentResolver.SCHEME_FILE -> File(uri.path).name
        ContentResolver.SCHEME_CONTENT -> getCursorContent(uri)
        else -> null
    }
    private fun Context.getCursorContent(uri: Uri): String?
            = try {
        contentResolver.query(uri, null, null, null, null)?.let { cursor ->
            cursor.run {
                if (moveToFirst()) getString(getColumnIndex(OpenableColumns.DISPLAY_NAME))
                else null
            }.also { cursor.close() }
        }
    } catch (e : Exception) { null }

    fun SubmitProcessActivity.createTmpFile(it: String, fileName: String): File? {
        val pickFile: File = File(baseContext.cacheDir, fileName)
        try {

            val inputStream = ByteArrayInputStream(it.toByteArray(Charsets.UTF_8))
            val outputStream = FileOutputStream(pickFile)
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
        } catch (io: IOException) {
            Log.e("create: ", "", io)
        }
        return pickFile
    }

}