package com.sideki.imdb_app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String
)
