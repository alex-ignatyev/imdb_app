package com.sideki.imdb_app.ui.base

interface Reducer<S: State, A: Action> {

    fun reduce(currentState: S, action: Action): S
}
