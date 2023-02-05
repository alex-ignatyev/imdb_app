package com.sideki.imdb_app.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val login = MutableStateFlow("")
    val password = MutableStateFlow("")
    val loginError = MutableLiveData<String?>()
    val passwordError = MutableLiveData<String?>()

    fun loginValidation(input: String) {
        login.value = input
    }

    fun passwordValidation(input: String) {
        password.value = input
    }

    fun logIn() {
        viewModelScope.launch {
            val userAccount = accountRepository.getAccount(login.value)
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