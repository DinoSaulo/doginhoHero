package br.ufpe.cin.android.doguinhoHero.data2.contaBancaria

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ContaBancariaRepository(private val contaBancariaDAO: ContaBancariaDAO) {

    val readAllData: LiveData<List<ContaBancaria>> = contaBancariaDAO.getAllContasBancarias()

    suspend fun addContaBancaria(contaBancaria: ContaBancaria){
        contaBancariaDAO.addContaBancaria(contaBancaria)
    }

    @WorkerThread
    fun findContaBancaria(contaBancariaId: Long): ContaBancaria {
        return contaBancariaDAO.findContaBancariaById(contaBancariaId)
    }

}