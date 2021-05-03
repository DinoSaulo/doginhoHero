package br.ufpe.cin.android.doguinhoHero.data2.usuarios

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User): Long


    @Update
    suspend fun update(user: User)
    //atualizar(p1,p2,p3)

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM user_table WHERE email = :email")
    fun findUser(email: String) : User

    @Query("SELECT * FROM user_table")
    fun getAll() : LiveData<List<User>>

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<User>>
}