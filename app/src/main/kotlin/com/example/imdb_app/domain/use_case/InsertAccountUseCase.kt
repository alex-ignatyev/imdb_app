package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.model.AccountModel
import com.example.imdb_app.domain.repository.AccountRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun insertAccount(account: AccountModel) {
        withContext(Dispatchers.IO) {
            repo.insertAccount(account)
        }
    }
}
