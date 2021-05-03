package br.ufpe.cin.android.doguinhoHero.data2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.ufpe.cin.android.doguinhoHero.data2.cartao.Cartao
import br.ufpe.cin.android.doguinhoHero.data2.cartao.CartaoDAO
import br.ufpe.cin.android.doguinhoHero.data2.contaBancaria.ContaBancaria
import br.ufpe.cin.android.doguinhoHero.data2.contaBancaria.ContaBancariaDAO
import br.ufpe.cin.android.doguinhoHero.data2.passeio.Passeio
import br.ufpe.cin.android.doguinhoHero.data2.passeio.PasseioDAO
import br.ufpe.cin.android.doguinhoHero.data2.pet.Pet
import br.ufpe.cin.android.doguinhoHero.data2.pet.PetDAO
import br.ufpe.cin.android.doguinhoHero.data2.usuarios.User
import br.ufpe.cin.android.doguinhoHero.data2.usuarios.UserDAO

@Database(entities = [User::class, Passeio::class, ContaBancaria::class, Pet::class, Cartao::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
    abstract fun passeioDAO() : PasseioDAO
    abstract fun contaBancariaDAO() : ContaBancariaDAO
    abstract fun petDAO() : PetDAO
    abstract fun cartaoDAO() : CartaoDAO

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}