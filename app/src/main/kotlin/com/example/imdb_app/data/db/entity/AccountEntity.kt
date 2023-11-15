package com.example.imdb_app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int? = null,
    val login: String,
    val password: String
)
