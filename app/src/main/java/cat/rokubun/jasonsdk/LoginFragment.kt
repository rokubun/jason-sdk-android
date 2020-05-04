package cat.rokubun.jasonsdk

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import cat.rokubun.jason.JasonClient
import cat.rokubun.jason.User

import cat.rokubun.jasonsdk.R
import cat.rokubun.jasonsdk.utlis.Permissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Login to Jason
 */
class LoginFragment : Fragment() {
    @BindView(R.id.connectButton)
    lateinit var connect: Button
    @BindView(R.id.passwordEditText)
    lateinit var passwordEditText: EditText
    @BindView(R.id.userEditText)
    lateinit var userEditText: EditText
    var user: User? = null
    var email: String ? = null
    var password: String ? = null
    private val processViewModel: ProcessViewModel by lazy {
        ViewModelProvider(this).get(ProcessViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        Permissions.checkPermissions(this.activity!!, requiredPermissions)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{

        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
        ButterKnife.bind(this, view)
        return view
    }


    @OnClick(R.id.connectButton)
    fun login() {
        if (validate()) {
            email = userEditText.text.toString()
            password = passwordEditText.text.toString()
            processViewModel.loginToJason(email!!, password!!)
            processViewModel.userLiveData.observe(this, Observer {
                if (it.secretToken.equals("")) {
                    Toast.makeText(context, "Can not connect", Toast.LENGTH_SHORT).show()
                } else {

                    Navigation.findNavController(this.view!!).navigate(R.id.action_login_to_submit)
                }
            })
        }
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
