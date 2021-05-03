package br.ufpe.cin.android.doguinhoHero.data2.contaBancaria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.ufpe.cin.android.doguinhoHero.data2.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContaBancariaViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<ContaBancaria>>
    val contaBancaria = MutableLiveData<ContaBancaria>()
    private val repository: ContaBancariaRepository

    init {
        val contaBancariaDAO = AppDatabase.getDatabase(application).contaBancariaDAO()
        repository = ContaBancariaRepository(contaBancariaDAO)
        readAllData = repository.readAllData
    }

    fun addContaBancaria(contaBancaria: ContaBancaria){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addContaBancaria(contaBancaria)
        }
    }

}