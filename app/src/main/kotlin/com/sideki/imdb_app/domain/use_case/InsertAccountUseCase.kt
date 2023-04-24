package com.sideki.imdb_app.domain.use_case

import com.sideki.imdb_app.domain.AccountRepository
import com.sideki.imdb_app.model.entity.AccountEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun insertAccount(account: AccountEntity) {
        withContext(Dispatchers.IO) {
            repo.insertAccount(account)
        }
    }
}
