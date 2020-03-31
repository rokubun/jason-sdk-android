package cat.rokubun.jasonsdk

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jasonsdk.utlis.Permissions
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    @BindView(R.id.connectButton)
    lateinit var connect: Button
    @BindView(R.id.passwordEditText)
    lateinit var passwordEditText: EditText
    @BindView(R.id.userEditText)
    lateinit var userEditText: EditText
    var user: User? = null
    var email: String ? = null
    var password: String ? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val requiredPermissions = arrayOf<String>(
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        )
        Permissions.checkPermissions(this, requiredPermissions)
    }

    @OnClick(R.id.connectButton)
    fun login() {
        //TODO CONTROL ERRORS
        if (validate()) {
            JasonClient.login(this.email, this.password)?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe()
            loginVerification()

        }
    }

    private fun loginVerification() {
        JasonClient.codeResponse.observe(this, Observer {
            when (it.code) {
                200 -> {
                    Toast.makeText(baseContext, it.description, Toast.LENGTH_SHORT).show()
                    user = JasonClient.user
                    val intent = Intent(this, SubmitProcessActivity::class.java).apply {
                        putExtra("TOKEN", JasonClient.user?.secretToken)
                        Log.d("token", JasonClient.user?.secretToken)
                        startActivity(intent)
                    }
                    startActivity(intent)
                }
                500 -> Toast.makeText(baseContext, it.description, Toast.LENGTH_SHORT).show()
                401 -> Toast.makeText(baseContext, it.description, Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validate():Boolean{
        var valid = true
        email = userEditText.text.toString()
        password = passwordEditText.text.toString()

        if(email!!.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEditText.error = "Enter a valid email address"
            valid = false
        } else {
            userEditText.error = null
        }
        if (password!!.isEmpty()){
            passwordEditText.error = "Enter a password"
            valid = false
        }else{
            passwordEditText.error = null
        }
        return valid
    }


}

