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
    val isButtonEnabled = MutableLiveData<Boolean>()
    val isFilledCorrectly = MutableLiveData<Boolean>()

    fun loginValidation(input: String) {
        login.value = input
        disableButton()
    }

    fun passwordValidation(input: String) {
        password.value = input
        disableButton()
    }

    private fun disableButton() {
        isButtonEnabled.value =
            login.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    fun logIn() {
        viewModelScope.launch {
            val userAccount = accountRepository.getAccount(login.value)
            if (userAccount != null) {
                loginError.value = null
                if (userAccount.password == password.value) {
                    isFilledCorrectly.value = true
                } else {
                    passwordError.value = "Invalid password"
                }
            } else {
                loginError.value = "Account with this name does not exist"
            }
        }
    }
}