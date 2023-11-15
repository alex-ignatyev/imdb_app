package com.example.imdb_app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb_app.data.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(account: AccountEntity)

    @Query("SELECT * FROM account_table WHERE login LIKE :account")
    suspend fun getAccount(account: String): AccountEntity?

    @Query("UPDATE account_table SET password = :password")
    suspend fun changePassword(password: String)
}
