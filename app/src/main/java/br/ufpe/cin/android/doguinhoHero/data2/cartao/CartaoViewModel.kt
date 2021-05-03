package br.ufpe.cin.android.doguinhoHero.data2.cartao

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.ufpe.cin.android.doguinhoHero.data2.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartaoViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<Cartao>>
    val cartao = MutableLiveData<Cartao>()
    private val repository: CartaoRepository

    init {
        val cartaoDAO = AppDatabase.getDatabase(application).cartaoDAO()
        repository = CartaoRepository(cartaoDAO)
        readAllData = repository.readAllData
    }

    fun addCartao(cartao: Cartao){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCartao(cartao)
        }
    }

}