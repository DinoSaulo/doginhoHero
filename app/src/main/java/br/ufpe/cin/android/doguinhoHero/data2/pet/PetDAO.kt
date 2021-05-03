package br.ufpe.cin.android.doguinhoHero.data2.pet

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface PetDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addContaBancaria(pet: Pet): Long

    @Update
    fun update(pet: Pet)

    // delete in cascade
    @Query("DELETE FROM pets WHERE petId = :petId")
    suspend fun delete(petId: Long)

    @Query("SELECT * FROM pets WHERE petId = :petId LIMIT 1")
    fun findPetById(petId: Long) : Pet

    @Query("SELECT * FROM pets WHERE nome = :nome")
    fun findPetByNome(nome: String) : LiveData<List<Pet>>

    @Query("SELECT * FROM pets")
    fun getAllPets() : LiveData<List<Pet>>

}