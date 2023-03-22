package com.sideki.imdb_app.ui.base

import kotlinx.coroutines.flow.MutableStateFlow

class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>
) {

    val state = MutableStateFlow(initialState)

    fun dispatch(action: A) {
        val currentState = state.value
        val newState = reducer.reduce(currentState, action)
        state.value = newState
    }
}
