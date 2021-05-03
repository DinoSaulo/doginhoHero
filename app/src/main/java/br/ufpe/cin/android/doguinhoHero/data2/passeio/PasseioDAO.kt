package br.ufpe.cin.android.doguinhoHero.data2.passeio

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface PasseioDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun addPasseio(passeio: Passeio): Long

    @Update
     fun update(passeio: Passeio)

    @Query("DELETE FROM passeios WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM passeios WHERE id = :id LIMIT 1")
    fun findPasseioById(id: Long) : Passeio

    @Query("SELECT * FROM passeios WHERE petId = :petId")
    fun findPasseioByPetId(petId: Long) : Passeio

    @Query("SELECT * FROM passeios WHERE donoDoPetId = :donoDoPetId")
    fun findPasseioByDonoDoPetId(donoDoPetId: Long) : Passeio

    @Query("SELECT * FROM passeios WHERE petHeroId = :petHeroId")
    fun findPasseioByPetHeroId(petHeroId: Long) : Passeio

    @Query("SELECT * FROM passeios")
    fun getAllPasseios() : LiveData<List<Passeio>>

}