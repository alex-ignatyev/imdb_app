package com.example.imdb_app.domain.repository

import com.example.imdb_app.domain.model.AccountModel

interface AccountRepository {

    suspend fun insertAccount(account: AccountModel)

    suspend fun getAccount(account: String): AccountModel?

    suspend fun changePassword(password: String)
}
