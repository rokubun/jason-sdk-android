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
    var user: User? =  null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.connectButton)
    fun login(){
        //TODO CONTROL ERRORS
        val email = userEditText.text.toString()
        val pass = passwordEditText.text.toString()
        if (verification(email, pass)){
            if(JasonClient.login(email, pass)){
                Toast.makeText(this, "Connection Success!!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Login error!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun verification(email: String, pass: String): Boolean{
        if(!email.isEmpty() && !pass.isEmpty()){
            return true
        }else{
            Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_LONG).show()
            return false
        }

    }
}
