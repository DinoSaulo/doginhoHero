package br.ufpe.cin.android.doguinhohero.data2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val email: String,
    val senha: String,
    val tipoDeUsuario: String,
    val cep: String,
    val numero: String,
    //val complemento: String,
    val rua: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val latitude: String,
    val longitude: String
)
