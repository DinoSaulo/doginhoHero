package br.ufpe.cin.android.doguinhoHero.data2.pet

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PetRepository(private val contaBancariaDAO: PetDAO) {

    val readAllData: LiveData<List<Pet>> = contaBancariaDAO.getAllPets()

    suspend fun addPet(pet: Pet){
        contaBancariaDAO.addContaBancaria(pet)
    }

    @WorkerThread
    fun findPetById(petId: Long): Pet {
        return contaBancariaDAO.findPetById(petId)
    }

}