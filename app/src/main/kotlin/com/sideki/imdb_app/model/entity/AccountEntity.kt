package com.sideki.imdb_app.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int? = null,
    val login: String,
    val password: String
)
