package br.ufpe.cin.android.doguinhohero.data2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long


    @Update
    suspend fun update(user: User)
    //atualizar(p1,p2,p3)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT nome FROM user_table WHERE email = :email")
    suspend fun findUser(email: String) : String

    @Query("SELECT * FROM user_table")
    fun getAll() : LiveData<List<User>>

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
}