package com.sideki.imdb_app.ui.change_password

import androidx.lifecycle.ViewModel
import com.sideki.imdb_app.ui.base.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ChangePasswordVM @Inject constructor() : ViewModel() {

    private val store = Store(
        initialState = ChangePasswordState(),
        reducer = ChangePasswordReducer()
    )

    val state: StateFlow<ChangePasswordState> = store.state

    fun obtainCurrentPasswordChanges(input: String) {
        val action = ChangePasswordAction.CurrentPasswordChanged(input)
        store.dispatch(action)
    }

    fun obtainNewPasswordChanges(input: String) {
        val action = ChangePasswordAction.NewPasswordChanged(input)
        store.dispatch(action)
    }

    fun obtainRepeatNewPasswordChanges(input: String) {
        val action = ChangePasswordAction.RepeatNewPasswordChanged(input)
        store.dispatch(action)
    }

}
