package br.ufpe.cin.android.doguinhoHero.data2.passeio

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class PasseioRepository(private val passeioDAO: PasseioDAO) {

    val readAllData: LiveData<List<Passeio>> = passeioDAO.getAllPasseios()

    suspend fun addPasseio(passeio: Passeio){
        passeioDAO.addPasseio(passeio)
    }

    @WorkerThread
    fun findPasseio(passeioId: Long): Passeio {
        return passeioDAO.findPasseioById(passeioId)
    }

}