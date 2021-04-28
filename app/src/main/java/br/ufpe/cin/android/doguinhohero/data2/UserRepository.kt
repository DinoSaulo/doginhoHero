package br.ufpe.cin.android.doguinhohero.data2

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDAO) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun findUser(email: String){
        userDao.findUser(email)
    }

}