package com.sideki.imdb_app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sideki.imdb_app.db.entity.AccountEntity

@Dao
interface AccountsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(account: AccountEntity)

    @Query("SELECT * FROM account_table WHERE login LIKE :login")
    suspend fun getUsername(login: String): AccountEntity?
}
