package com.example.movieapp.ui.login

import androidx.lifecycle.ViewModel
import io.reactivex.subjects.PublishSubject
import java.util.regex.Pattern

/**
 * Create by Linh Le H. M. on 11/16/20
 */

class LoginViewModel : ViewModel(), LoginMVContract {

    companion object {
        internal const val MAX_EMAIL_LENGTH = 264
    }

    private val validateLoginInformationStatus = PublishSubject.create<Boolean>()
    private val passwordPattern = Pattern.compile("""^(?=.*).{8,16}$""")

    override fun login(email: String, password: String): Boolean =
        email == "admin@gmail.com" && password == "admin123"

    override fun infoValidateStatus(): PublishSubject<Boolean> = validateLoginInformationStatus

    override fun validateLoginInformation(email: String, password: String) {
        if (passwordPattern.matcher(password).matches()
            && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            && email.length <= MAX_EMAIL_LENGTH
        ) {
            validateLoginInformationStatus.onNext(true)
        } else {
            validateLoginInformationStatus.onNext(false)
        }
    }
}
