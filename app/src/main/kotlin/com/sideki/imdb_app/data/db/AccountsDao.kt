package com.sideki.imdb_app.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideki.imdb_app.model.entity.AccountEntity

@Dao
interface AccountsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(account: AccountEntity)

    @Query("SELECT * FROM account_table WHERE login LIKE :account")
    suspend fun getAccount(account: String): AccountEntity?
}
