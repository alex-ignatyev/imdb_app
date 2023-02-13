package com.sideki.imdb_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginVM @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase
) : ViewModel() {

    val login = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun loginValidation(input: String) {
        //Todo
    }

    fun passwordValidation(input: String) {
        //Todo
    }

    fun logIn() {
        viewModelScope.launch {
            val userAccount = getAccountUseCase.getAccount(login.value)
            if (userAccount != null) {
                if (userAccount.password == password.value) {
                    //Todo
                } else {
                    //Todo
                }
            } else {
                //Todo
            }
        }
    }
}
