package com.example.imdb_app.domain.model

import com.example.imdb_app.data.db.entity.AccountEntity

data class AccountModel(
    val userId: Int? = null,
    val login: String = "",
    val password: String = ""
)

fun AccountModel.toEntity() = AccountEntity(
    userId = userId,
    login = login,
    password = password
)

fun AccountEntity.toModel() =
    AccountModel(
        userId = userId ?: 0,
        login = login,
        password = password
    )

