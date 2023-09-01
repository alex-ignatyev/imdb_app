package com.example.imdb_app.domain.use_case

import com.example.imdb_app.domain.AccountRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChangePasswordUseCase @Inject constructor(
    private val repo: AccountRepository
) {

    suspend fun changePassword(password: String) {
        withContext(Dispatchers.IO) {
            repo.changePassword(password)
        }
    }
}
