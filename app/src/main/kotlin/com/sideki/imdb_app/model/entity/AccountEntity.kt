package com.sideki.imdb_app.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val userId: Int,
    val firstName: String,
    val login: String,
    val password: String
)