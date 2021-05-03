package br.ufpe.cin.android.doguinhoHero.data2.cartao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface CartaoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCartao(cartao: Cartao): Long

    @Update
    fun update(cartao: Cartao)

    // delete in cascade
    @Query("DELETE FROM cartoes WHERE cartaoId = :cartaoId")
    suspend fun delete(cartaoId: Long)

    @Query("SELECT * FROM cartoes WHERE cartaoId = :cartaoId LIMIT 1")
    fun findCartaoById(cartaoId: Long) : Cartao

    /*
    @Query("SELECT * FROM cartoes JOIN user_table ON (user_table.cartaoId = cartoes.cartaoId)WHERE cartaoId = :cartaoId")
    fun findCartaoByUsuarioId(cartaoId: Long) : Cartao
     */

    @Query("SELECT * FROM cartoes")
    fun getAllCartoes() : LiveData<List<Cartao>>

}