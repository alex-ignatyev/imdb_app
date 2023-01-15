package com.sideki.imdb_app.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sideki.imdb_app.db.entity.AccountEntity

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: AccountEntity)

    @Query("SELECT * FROM account_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<AccountEntity>>?

    @Query("SELECT * FROM account_table WHERE userName LIKE :userName")
    suspend fun getUsername(userName: String): AccountEntity?
}
