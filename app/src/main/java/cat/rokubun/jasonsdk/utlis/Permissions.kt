package cat.rokubun.jasonsdk.utlis

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

object Permissions {
    private const val REQUEST_CODE_ASK_PERMISSIONS = 1
    fun checkPermissions(
        activity: Activity,
        requiredPermissions: Array<String>
    ) {
        val missingPermissions: MutableList<String> =
            ArrayList()
        // check all required dynamic permissions
        for (permission in requiredPermissions) {
            val result =
                ContextCompat.checkSelfPermission(activity.applicationContext, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission)
            }
        }
        if (!missingPermissions.isEmpty()) { // request all missing permissions
            val permissions = missingPermissions
                .toTypedArray()
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                REQUEST_CODE_ASK_PERMISSIONS
            )
        } else {
            val grantResults = IntArray(requiredPermissions.size)
            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED)
            onRequestPermissionsResult(
                REQUEST_CODE_ASK_PERMISSIONS,
                requiredPermissions,
                grantResults
            )
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> {
                var index = permissions.size - 1
                while (index >= 0) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) { // exit the app if one permission is not granted
// Toast.makeText(MainActivity., "Required permission '" + permissions[index] + "' not granted, exiting", Toast.LENGTH_LONG).show();
// finish();
                        return
                    }
                    --index
                }
            }
        }
    }
}