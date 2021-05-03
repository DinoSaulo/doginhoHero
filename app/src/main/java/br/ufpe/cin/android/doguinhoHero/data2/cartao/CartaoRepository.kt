package br.ufpe.cin.android.doguinhoHero.data2.cartao

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CartaoRepository(private val cartaoDAO: CartaoDAO) {

    val readAllData: LiveData<List<Cartao>> = cartaoDAO.getAllCartoes()

    suspend fun addCartao(contaBancaria: Cartao){
        cartaoDAO.addCartao(contaBancaria)
    }

    @WorkerThread
    fun findCartao(cartaoId: Long): Cartao {
        return cartaoDAO.findCartaoById(cartaoId)
    }

}