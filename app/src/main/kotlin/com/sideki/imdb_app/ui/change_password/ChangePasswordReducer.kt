package com.sideki.imdb_app.ui.change_password

import com.sideki.imdb_app.ui.base.Action
import com.sideki.imdb_app.ui.base.Reducer

class ChangePasswordReducer : Reducer<ChangePasswordState, ChangePasswordAction> {
    override fun reduce(currentState: ChangePasswordState, action: Action): ChangePasswordState {
        return when (action) {
            is ChangePasswordAction.CurrentPasswordChanged -> {
                currentState.copy(
                    currentPassword = action.currentPassword
                )
            }
            is ChangePasswordAction.NewPasswordChanged -> {
                currentState.copy(
                    newPassword = action.newPassword
                )
            }
            is ChangePasswordAction.RepeatNewPasswordChanged -> {
                currentState.copy(
                    repeatNewPassword = action.repeatNewPassword
                )
            }
            else -> currentState
        }
    }
}
