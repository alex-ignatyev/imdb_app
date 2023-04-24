package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.model.entity.AccountEntity
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun getAccount(account: String): AccountEntity? {
        return repo.getAccount(account)
    }
}
