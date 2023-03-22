package com.sideki.imdb_app.ui.change_password

import com.sideki.imdb_app.ui.base.Action

sealed class ChangePasswordAction : Action {
    data class CurrentPasswordChanged(val currentPassword: String) : ChangePasswordAction()
    data class NewPasswordChanged(val newPassword: String) : ChangePasswordAction()
    data class RepeatNewPasswordChanged(val repeatNewPassword: String) : ChangePasswordAction()
    object ChangePasswordButtonClicked : ChangePasswordAction()
}
