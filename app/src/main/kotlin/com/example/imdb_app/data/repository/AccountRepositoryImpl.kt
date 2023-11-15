package com.example.imdb_app.data.repository

import com.example.imdb_app.data.db.AccountsDao
import com.example.imdb_app.domain.model.AccountModel
import com.example.imdb_app.domain.model.toEntity
import com.example.imdb_app.domain.model.toModel
import com.example.imdb_app.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountsDao: AccountsDao
) : AccountRepository {

    override suspend fun insertAccount(account: AccountModel) {
        return accountsDao.insertAccount(account.toEntity())
    }

    override suspend fun getAccount(account: String): AccountModel? {
        return accountsDao.getAccount(account)?.toModel()
    }

    override suspend fun changePassword(password: String) {
        return accountsDao.changePassword(password)
    }
}
