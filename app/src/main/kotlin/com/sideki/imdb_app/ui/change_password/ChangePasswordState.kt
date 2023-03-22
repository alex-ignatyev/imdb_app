package com.sideki.imdb_app.ui.change_password

import com.sideki.imdb_app.ui.base.State

data class ChangePasswordState(
    val currentPassword: String = "",
    val currentPasswordError: Boolean = false,
    val newPassword: String = "",
    val newPasswordError: Boolean = false,
    val repeatNewPassword: String = "",
    val repeatNewPasswordError: Boolean = false
) : State
