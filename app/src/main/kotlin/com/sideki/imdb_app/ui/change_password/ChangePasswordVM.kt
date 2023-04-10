package com.sideki.imdb_app.ui.change_password

import androidx.lifecycle.viewModelScope
import com.sideki.imdb_app.db.DataStorePreferenceStorage
import com.sideki.imdb_app.domain.use_case.ChangePasswordUseCase
import com.sideki.imdb_app.domain.use_case.GetAccountUseCase
import com.sideki.imdb_app.ui.change_password.ChangePasswordAction.OnChangePasswordButtonClicked
import com.sideki.imdb_app.ui.change_password.ChangePasswordAction.CurrentPasswordChanged
import com.sideki.imdb_app.ui.change_password.ChangePasswordAction.NewPasswordChanged
import com.sideki.imdb_app.ui.change_password.ChangePasswordAction.RepeatNewPasswordChanged
import com.sideki.imdb_app.ui.change_password.ChangePasswordEffect.OpenProfileScreen
import com.sideki.imdb_app.util.base.BaseMVIViewModel
import com.sideki.imdb_app.util.base.UIAction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class ChangePasswordVM @Inject constructor() : BaseMVIViewModel<ChangePasswordState>(ChangePasswordState()) {

    @Inject lateinit var preferences: DataStorePreferenceStorage
    @Inject lateinit var changePasswordUseCase: ChangePasswordUseCase
    @Inject lateinit var getAccountUseCase: GetAccountUseCase

    override fun handleAction(action: UIAction) {
        when (action) {
            is CurrentPasswordChanged -> obtainCurrentPasswordChanges(action.currentPassword)
            is NewPasswordChanged -> obtainNewPasswordChanges(action.newPassword)
            is RepeatNewPasswordChanged -> obtainRepeatNewPasswordChanges(action.repeatNewPassword)
            is OnChangePasswordButtonClicked -> changePassword()
        }
    }

    private fun obtainCurrentPasswordChanges(input: String) {
        if (input.length <= 20) setState(currentState.copy(currentPassword = input))
    }

    private fun obtainNewPasswordChanges(input: String) {
        val error = if (input.length < 8) "Minimum 8 character"
        else if (input != currentState.repeatNewPassword) "Password mismatch" else ""
        if (input.length <= 20) setState(currentState.copy(newPassword = input, newPasswordError = error))
    }

    private fun obtainRepeatNewPasswordChanges(input: String) {
        val error = if (input.length < 8) "Minimum 8 character"
        else if (input != currentState.newPassword) "Password mismatch" else ""
        if (input.length <= 20) setState(currentState.copy(repeatNewPassword = input, repeatNewPasswordError = error))
    }


    fun changePassword() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val account = getAccountUseCase.getAccount(preferences.currentAccountLoggedIn.first())
                val isOldPasswordValid = currentState.currentPassword.contains(account?.password ?: "")
                if (isOldPasswordValid) {
                    changePasswordUseCase.changePassword(currentState.currentPassword)
                    setEffect(OpenProfileScreen())
                } else {
                    setState(currentState.copy(currentPasswordError = "Password mismatch"))
                }
            }
        }
    }
}
