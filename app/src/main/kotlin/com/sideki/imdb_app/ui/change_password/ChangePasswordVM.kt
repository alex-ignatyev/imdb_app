package com.sideki.imdb_app.ui.change_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.domain.use_case.ChangePasswordUseCase
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChangePasswordVM @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val preferences: DataStorePreferenceStorage
) : ViewModel() {

    val state = MutableStateFlow(ChangePasswordState())

    fun obtainCurrentPasswordChanges(input: String) {
        if (input.length <= 20) state.value = state.value.copy(currentPassword = input)
    }

    fun obtainNewPasswordChanges(input: String) {
        if (input.length <= 20) state.value = state.value.copy(
            newPassword = input,
            newPasswordError = input.length < 8 || input != state.value.repeatNewPassword,
            repeatNewPasswordError = false
        )
    }

    fun obtainRepeatNewPasswordChanges(input: String) {
        if (input.length <= 20) state.value = state.value.copy(
            repeatNewPassword = input,
            repeatNewPasswordError = input.length < 8 || input != state.value.newPassword,
            newPasswordError = false
        )
    }

    fun changePassword(passwordChanged: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val account = getAccountUseCase.getAccount(preferences.currentAccountLoggedIn.first())
                val currentPassword = account?.password
                if (currentPassword != state.value.currentPassword) state.value =
                    state.value.copy(currentPasswordError = true)
                else if (currentPassword != state.value.currentPassword && state.value.newPassword == state.value.repeatNewPassword) {
                    changePasswordUseCase.changePassword(state.value.newPassword)
                    withContext(Dispatchers.Main) { passwordChanged.invoke() }
                }
            }
        }
    }
}

data class ChangePasswordState(
    val currentPassword: String = "",
    val currentPasswordError: Boolean = false,
    val newPassword: String = "",
    val newPasswordError: Boolean = false,
    val repeatNewPassword: String = "",
    val repeatNewPasswordError: Boolean = false,
)
