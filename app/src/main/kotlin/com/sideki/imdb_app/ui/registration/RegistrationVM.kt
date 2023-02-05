package com.sideki.imdb_app.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.entity.AccountEntity
import com.sideki.imdb_app.domain.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class RegistrationVM @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {


    val login = MutableStateFlow("")
    val loginError = MutableLiveData<String?>()
    val name = MutableStateFlow("")
    val nameError = MutableLiveData<String?>()
    val password = MutableStateFlow("")
    val passwordError = MutableLiveData<String?>()
    val repeatPassword = MutableStateFlow("")
    val repeatPasswordError = MutableLiveData<String?>()
    val isButtonEnabled = MutableLiveData<Boolean>()
    val isAccountCreated = MutableLiveData<Boolean>()

    fun loginValidation(input: String) {
        login.value = input
        disableButton()
        loginError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun nameValidation(input: String) {
        name.value = input
        disableButton()
        nameError.value = if (input.isEmpty()) "The field must be filled in" else null
    }

    fun passwordValidation(input: String) {
        password.value = input
        disableButton()
        passwordEqualityCheck()
        passwordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    fun repeatPasswordValidation(input: String) {
        repeatPassword.value = input
        disableButton()
        passwordEqualityCheck()
        repeatPasswordError.value = if (input.length < 8) "Minimum 8 character" else null
    }

    private fun passwordEqualityCheck() {
        if (password.value != repeatPassword.value) {
            passwordError.value = "Password mismatch"
            repeatPasswordError.value = "Password mismatch"
        } else {
            passwordError.value = null
            repeatPasswordError.value = null
        }
    }

    private fun disableButton() {
        isButtonEnabled.value =
            login.value.trim().isNotEmpty() && name.value.trim().isNotEmpty() && password.value.trim()
                .isNotEmpty() && repeatPassword.value.trim().isNotEmpty() && password.value == repeatPassword.value
    }

    fun createAccount() {
        viewModelScope.launch {
            val userAccount = accountRepository.getAccount(login.value)
            if (userAccount != null) {
                loginError.value = "Account already exist"
            } else {
                withContext(Dispatchers.IO) {
                    accountRepository.insertAccount(AccountEntity(0, name.value, login.value, password.value))
                }
                isAccountCreated.value = true
            }
        }
    }
}
