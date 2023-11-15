package com.example.imdb_app.ui.change_password

import androidx.lifecycle.viewModelScope
import com.example.imdb_app.data.db.DataStorePreferenceStorage
import com.example.imdb_app.domain.use_case.ChangePasswordUseCase
import com.example.imdb_app.domain.use_case.GetAccountUseCase
import com.example.imdb_app.ui.change_password.ChangePasswordAction.CurrentPasswordChanged
import com.example.imdb_app.ui.change_password.ChangePasswordAction.NewPasswordChanged
import com.example.imdb_app.ui.change_password.ChangePasswordAction.OnBackButtonClicked
import com.example.imdb_app.ui.change_password.ChangePasswordAction.OnChangePasswordButtonClicked
import com.example.imdb_app.ui.change_password.ChangePasswordAction.RepeatNewPasswordChanged
import com.example.imdb_app.ui.change_password.ChangePasswordEffect.OpenProfileScreen
import com.example.imdb_app.util.base.BaseMVIViewModel
import com.example.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChangePasswordVM @Inject constructor(
    private val preferences: DataStorePreferenceStorage,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : BaseMVIViewModel<ChangePasswordState>(ChangePasswordState()) {

    override fun handleAction(action: UIAction) {
        when (action) {
            is CurrentPasswordChanged -> obtainCurrentPasswordChanges(action.currentPassword)
            is NewPasswordChanged -> obtainNewPasswordChanges(action.newPassword)
            is RepeatNewPasswordChanged -> obtainRepeatNewPasswordChanges(action.repeatNewPassword)
            is OnChangePasswordButtonClicked -> changePassword()
            is OnBackButtonClicked -> openProfileScreen()
        }
    }

    private fun obtainCurrentPasswordChanges(input: String) {
        if (input.length <= 20) setState(currentState.copy(currentPassword = input))
    }

    private fun obtainNewPasswordChanges(input: String) {
        val error = if (input.length < 8) "Minimum 8 character" else ""
        if (input.length <= 20) setState(currentState.copy(newPassword = input, newPasswordError = error))
    }

    private fun obtainRepeatNewPasswordChanges(input: String) {
        val error = if (input.length < 8) "Minimum 8 character"
        else if (input != currentState.newPassword) "Password mismatch" else ""
        if (input.length <= 20 && currentState.newPassword.isNotEmpty()) setState(
            currentState.copy(repeatNewPassword = input, repeatNewPasswordError = error)
        )
        if (currentState.newPassword != currentState.repeatNewPassword && currentState.repeatNewPassword.length >= 8)
            setState(
                currentState.copy(newPasswordError = "Password mismatch")
            ) else setState(currentState.copy(newPasswordError = ""))
    }

    private fun changePassword() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val account = getAccountUseCase.getAccount(preferences.currentAccountLoggedIn.first())
                setState(currentState.copy(currentPasswordError = ""))
                if (currentState.currentPassword == account?.password) {
                    changePasswordUseCase.changePassword(currentState.newPassword)
                    setEffect(OpenProfileScreen())
                } else {
                    setState(currentState.copy(currentPasswordError = "Password mismatch"))
                }
            }
        }
    }

    private fun openProfileScreen() = setEffect(OpenProfileScreen())
}
