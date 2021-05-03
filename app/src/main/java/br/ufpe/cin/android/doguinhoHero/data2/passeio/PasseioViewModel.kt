package br.ufpe.cin.android.doguinhoHero.data2.passeio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.ufpe.cin.android.doguinhoHero.data2.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PasseioViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Passeio>>
    val professor = MutableLiveData<Passeio>()
    private val repository: PasseioRepository

    init {
        val passeioDAO = AppDatabase.getDatabase(application).passeioDAO()
        repository = PasseioRepository(passeioDAO)
        readAllData = repository.readAllData
    }

    fun addPasseio(passeio: Passeio){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPasseio(passeio)
        }
    }

    fun findPasseio(passeioId: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            var prof = repository.findPasseio(passeioId)
            // Fazer isto em background lança uma exceção professor.value = prof
            withContext(Dispatchers.Main.immediate) {
                //professor.value = prof
            }
        }

        ///return prof

    }
}