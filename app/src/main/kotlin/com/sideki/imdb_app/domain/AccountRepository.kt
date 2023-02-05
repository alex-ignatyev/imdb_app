package com.sideki.imdb_app.domain

import com.sideki.imdb_app.db.AccountDao
import com.sideki.imdb_app.db.entity.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {

    suspend fun insertAccount(account: AccountEntity) {
        return accountDao.insertAccount(account)
    }

    suspend fun getAccount(account: String): AccountEntity? {
        return accountDao.getAccount(account)
    }
}
