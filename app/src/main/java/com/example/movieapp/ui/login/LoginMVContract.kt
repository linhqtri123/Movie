package com.example.movieapp.ui.login

import io.reactivex.subjects.PublishSubject

/**
 * Create by Linh Le H. M. on 11/16/20
 */
interface LoginMVContract {
    fun login(email: String, password: String): Boolean

    fun infoValidateStatus(): PublishSubject<Boolean>

    fun validateLoginInformation(email: String, password: String)

}
