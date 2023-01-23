package com.sideki.imdb_app.domain

import com.sideki.imdb_app.db.AccountsDao
import com.sideki.imdb_app.db.entity.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountsDao: AccountsDao
) {

    suspend fun insertAccount(account: AccountEntity) {
        return accountsDao.insertAccount(account)
    }

    suspend fun getAccount(account: String): String? {
        return accountsDao.getAccount(account)
    }
}
