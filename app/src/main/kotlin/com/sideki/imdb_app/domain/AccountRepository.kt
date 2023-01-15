package com.sideki.imdb_app.domain

import com.sideki.imdb_app.db.AccountDao
import com.sideki.imdb_app.db.entity.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    val users = accountDao.getAllUsers()

    suspend fun insert(user: AccountEntity) {
        return accountDao.insert(user)
    }

    suspend fun getUserName(userName: String): AccountEntity? {
        return accountDao.getUsername(userName)
    }
}
