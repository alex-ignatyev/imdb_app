package com.example.imdb_app.domain

import com.example.imdb_app.data.db.AccountsDao
import com.example.imdb_app.model.entity.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountsDao: AccountsDao
) {

    suspend fun insertAccount(account: AccountEntity) {
        return accountsDao.insertAccount(account)
    }

    suspend fun getAccount(account: String): AccountEntity? {
        return accountsDao.getAccount(account)
    }

    suspend fun changePassword(password: String) {
        return accountsDao.changePassword(password)
    }
}
