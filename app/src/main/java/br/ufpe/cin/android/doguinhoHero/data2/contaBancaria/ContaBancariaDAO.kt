package br.ufpe.cin.android.doguinhoHero.data2.contaBancaria

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface ContaBancariaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addContaBancaria(contaBancaria: ContaBancaria): Long

    @Update
    fun update(contaBancaria: ContaBancaria)

    // delete in cascade
    @Query("DELETE FROM contas_bancarias WHERE contaBancariaId = :contaBancariaId")
    suspend fun delete(contaBancariaId: Long)

    @Query("SELECT * FROM contas_bancarias WHERE contaBancariaId = :contaBancariaId LIMIT 1")
    fun findContaBancariaById(contaBancariaId: Long) : ContaBancaria

    @Query("SELECT * FROM contas_bancarias WHERE contaBancariaId = :contaBancariaId")
    fun findContaBancariaByUsuarioId(contaBancariaId: Long) : ContaBancaria

    @Query("SELECT * FROM contas_bancarias")
    fun getAllContasBancarias() : LiveData<List<ContaBancaria>>

}