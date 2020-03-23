package cat.rokubun.jasonsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.sdk.JasonClient
import cat.rokubun.sdk.domain.User

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
            JasonClient.login(this.email, this.password)
            if(JasonClient.isValid!!){
                Toast.makeText(this, "Connection Success!!", Toast.LENGTH_SHORT).show()
            } else {
                onLoginFailed()
                return
            }
        }
    }

    fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
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

