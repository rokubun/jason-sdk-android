package cat.rokubun.jasonsdk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.domain.User
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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

