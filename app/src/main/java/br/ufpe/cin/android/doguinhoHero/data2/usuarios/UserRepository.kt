package br.ufpe.cin.android.doguinhoHero.data2.usuarios

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDAO) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    @WorkerThread
    suspend fun findUser(email: String): User {
        return userDao.findUser(email)
    }

}
