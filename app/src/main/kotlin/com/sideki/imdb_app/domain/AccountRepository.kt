package com.sideki.imdb_app.domain

import com.sideki.imdb_app.db.AccountsDao
import com.sideki.imdb_app.db.entity.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountsDao: AccountsDao
) {

    suspend fun insertUser(user: AccountEntity) {
        return accountsDao.insert(user)
    }

    suspend fun getLogin(login: String): AccountEntity? {
        return accountsDao.getUsername(login)
    }
}
