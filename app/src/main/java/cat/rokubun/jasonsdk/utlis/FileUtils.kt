package cat.rokubun.jasonsdk.utlis

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File

/**
 * Auxiliary File class
 */

class FileUtils (val context: Context){

    fun getFileName(uri: Uri?): String?
            = when (uri?.scheme) {
        ContentResolver.SCHEME_FILE -> File(uri.path).name
        ContentResolver.SCHEME_CONTENT -> getCursorContent(uri)
        else -> null
    }
    private fun getCursorContent(uri: Uri): String?
            = try {
        context.contentResolver.query(uri, null, null, null, null)?.let { cursor ->
            cursor.run {
                if (moveToFirst()) getString(getColumnIndex(OpenableColumns.DISPLAY_NAME))
                else null
            }.also { cursor.close() }
        }
    } catch (e : Exception) { null }



}