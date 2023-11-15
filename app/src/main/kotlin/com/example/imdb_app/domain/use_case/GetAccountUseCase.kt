package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.model.AccountModel
import com.example.imdb_app.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun getAccount(account: String): AccountModel? {
        return repo.getAccount(account)
    }
}
