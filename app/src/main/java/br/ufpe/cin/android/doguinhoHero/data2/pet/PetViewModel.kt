package br.ufpe.cin.android.doguinhoHero.data2.pet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.ufpe.cin.android.doguinhoHero.data2.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Pet>>
    val pet = MutableLiveData<Pet>()
    private val repository: PetRepository

    init {
        val petDAO = AppDatabase.getDatabase(application).petDAO()
        repository = PetRepository(petDAO)
        readAllData = repository.readAllData
    }

    fun addPet(pet: Pet){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPet(pet)
        }
    }

}