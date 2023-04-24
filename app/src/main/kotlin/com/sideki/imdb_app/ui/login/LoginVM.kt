package com.sideki.imdb_app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class LoginVM @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val preferences: DataStorePreferenceStorage
) : ViewModel() {

    val state = MutableStateFlow(LogInState())

    fun obtainLoginChanges(input: String) {
        state.value = state.value.copy(login = input, loginError = null)
    }

    fun obtainPasswordChanges(input: String) {
        state.value = state.value.copy(password = input, passwordError = null)
    }

    fun logIn(userLoggedIn: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            with(state.value) {
                val userAccount = getAccountUseCase.getAccount(login)
                if (userAccount != null) {
                    state.value = copy(loginError = null)
                    if (userAccount.password == password) {
                        val job = launch {
                            preferences.saveLoggedInState(true)
                            preferences.saveCurrentAccountLoggedIn(login)
                        }
                        job.join()
                    } else {
                        state.value =
                            copy(
                                passwordError = if (state.value.password.isEmpty()) "The field must be filled in"
                                else "Invalid password"
                            )
                    }
                } else {
                    state.value =
                        copy(
                            loginError = if (state.value.login.isEmpty()) "The field must be filled in"
                            else "Account with this name does not exist"
                        )
                }
            }
        }
    }
}

data class LogInState(
    val login: String = "",
    val loginError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
