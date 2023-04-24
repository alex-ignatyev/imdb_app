package com.sideki.imdb_app.ui.change_password

import com.sideki.imdb_app.util.base.UIAction
import com.sideki.imdb_app.util.base.UIEffect
import com.sideki.imdb_app.util.base.UIState

sealed class ChangePasswordAction : UIAction {
    data class CurrentPasswordChanged(val currentPassword: String) : ChangePasswordAction()
    data class NewPasswordChanged(val newPassword: String) : ChangePasswordAction()
    data class RepeatNewPasswordChanged(val repeatNewPassword: String) : ChangePasswordAction()
    class OnChangePasswordButtonClicked : ChangePasswordAction()
    class OnBackButtonClicked : ChangePasswordAction()
}

data class ChangePasswordState(
    val currentPassword: String = "",
    val currentPasswordError: String = "",
    val newPassword: String = "",
    val newPasswordError: String = "",
    val repeatNewPassword: String = "",
    val repeatNewPasswordError: String = ""
) : UIState

sealed class ChangePasswordEffect : UIEffect {
    class OpenProfileScreen : ChangePasswordEffect()
}
