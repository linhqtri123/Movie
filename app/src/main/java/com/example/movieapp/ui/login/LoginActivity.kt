package com.example.movieapp.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Create by Linh Le H. M. on 11/16/20
 */

class LoginActivity : AppCompatActivity() {

    private var viewModel: LoginMVContract? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = LoginViewModel()
        handleLoginEmailTextChanged()
        handleLoginPasswordTextChanged()
        setBackGroundLoginButton()
        handleClickingLoginButton()
    }

    private fun handleLoginEmailTextChanged() {
        outlinedTextFieldUsername.editText?.addTextChangedListener(onTextChanged = { p0: CharSequence?, _, _, _ ->
            viewModel?.validateLoginInformation(
                p0.toString(),
                outlinedTextFieldPassword.editText?.text.toString()
            )
        })
    }

    private fun handleLoginPasswordTextChanged() {
        outlinedTextFieldPassword.editText?.addTextChangedListener(onTextChanged = { p0: CharSequence?, _, _, _ ->
            viewModel?.validateLoginInformation(
                outlinedTextFieldUsername.editText?.text.toString(),
                p0.toString()
            )
        })
    }

    @SuppressLint("CheckResult")
    private fun setBackGroundLoginButton() {
        viewModel?.infoValidateStatus()?.subscribe({
            btnLogin?.isEnabled = it
            if (it) {
                btnLogin?.setBackgroundResource(R.drawable.bg_enabled_button)
            } else {
                btnLogin?.setBackgroundResource(R.drawable.bg_disabled_login_button)
            }
        }, {
            //No-op
        })
    }

    private fun handleClickingLoginButton() {
        btnLogin?.setOnClickListener {
            if (viewModel?.login(
                    outlinedTextFieldUsername?.editText?.text.toString(),
                    outlinedTextFieldPassword?.editText?.text.toString()
                ) == true
            ) {
                Toast.makeText(
                    this,
                    "Login Success",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    "Email or Password is incorrect",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
