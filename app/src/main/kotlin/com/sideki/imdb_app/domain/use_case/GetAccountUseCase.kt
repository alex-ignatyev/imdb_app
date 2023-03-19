package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.db.entity.AccountEntity
import com.sideki.imdb_app.domain.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun getAccount(account: String): AccountEntity? {
        return repo.getAccount(account)
    }
}