package br.ufpe.cin.android.doguinhoHero.data2.usuarios

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.ufpe.cin.android.doguinhoHero.data2.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    val professor = MutableLiveData<User>()
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun findUser(email: String) {
        var prof: User

        viewModelScope.launch(Dispatchers.IO) {
            prof = repository.findUser(email)
            // Fazer isto em background lança uma exceção professor.value = prof
            withContext(Dispatchers.Main.immediate) {
                professor.value = prof

            }
        }

        //return 'A'

    }

}