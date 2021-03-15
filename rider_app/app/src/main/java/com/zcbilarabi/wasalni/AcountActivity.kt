package com.zcbilarabi.wasalni

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.zcbilarabi.wasalni.dao.UserDao
import com.zcbilarabi.wasalni.modal.User
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_acount.*

class AcountActivity : AppCompatActivity() {
    var usedao: UserDao? = null
    var progdialog: ProgressDialog? = null

    enum class AccountStatus(var status: String) {
        LOGIN("login"), SIGNUP("signup")
    }
    var status: AccountStatus = AccountStatus.LOGIN
    fun Gotohomepage() {startActivity(Intent(this.baseContext,RiderActivity::class.java)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acount)
        //init dao
        usedao = UserDao()
        ParseUser.logOut()
        //init PrgDialog
        progdialog = ProgressDialog(this)
        progdialog?.setMessage("Pleaze Wait...")
        progdialog?.setCancelable(true)
        btnSignupLogin.text = AccountStatus.LOGIN.toString()
        textViewSignupLogin.text = AccountStatus.SIGNUP.toString()
        if (usedao?.checkLoggedIn()!!){
            Gotohomepage()
        }





    }





    fun doSignupOrLogin(view: View) {
        if (status == AccountStatus.LOGIN) {
// sign in the app
           var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "")
           Login(user)
            //usedao?.SignUp(user2)
        } else if (status == AccountStatus.SIGNUP) {
            progdialog?.show()
            var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "")
            usedao?.signUpWithCallback(user, { returnedUser ->
                progdialog?.hide()
                if (returnedUser.userName != null) {
                    Login(returnedUser)
                }
            })
        }

// sign up the app
        }

    fun ActiveDefaultBehaviour(view: View) {
        var tv = view as TextView
        if (tv.text == AccountStatus.LOGIN.toString()) {
            status = AccountStatus.LOGIN
        } else if (tv.text == AccountStatus.SIGNUP.toString()) {
            status = AccountStatus.SIGNUP
        }
        if (status == AccountStatus.LOGIN) {
            //sign in
            btnSignupLogin.text = AccountStatus.LOGIN.toString()
            textViewSignupLogin.text = AccountStatus.SIGNUP.toString()

        } else if (status == AccountStatus.SIGNUP) {
            //sign up
            status = AccountStatus.SIGNUP
            btnSignupLogin.text = AccountStatus.SIGNUP.toString()
            textViewSignupLogin.text = AccountStatus.LOGIN.toString()
        }
    }


    fun Login(user:User){
        progdialog?.show()
        var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "")
        usedao?.LogInWithCallback(user, { returnedUser ->
            progdialog?.hide()
            if (returnedUser.userName != null) {
                //Go to home page
                Gotohomepage()
            }else{
                progdialog?.hide()
                Toast.makeText(this,"انت غير مسجل في التطبيق",Toast.LENGTH_LONG).show()

            }
        })
    }

}

