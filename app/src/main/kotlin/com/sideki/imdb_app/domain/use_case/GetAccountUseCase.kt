package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.model.entity.AccountEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun getAccount(account: String): AccountEntity? {
        return repo.getAccount(account)
    }
}