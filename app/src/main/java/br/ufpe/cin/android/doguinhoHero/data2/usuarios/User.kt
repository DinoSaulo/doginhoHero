package br.ufpe.cin.android.doguinhoHero.data2.usuarios

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    @NonNull
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
        /*
    @ForeignKey
    val petId,
    @ForeignKey
    val cartaoDeCreditoId,
    @ForeignKey
    val contaBancariaId
    */

) {
    override fun toString(): String {
        return "User(id=$id, nome='$nome', email='$email', senha='$senha', tipoDeUsuario='$tipoDeUsuario', cep='$cep', numero='$numero', rua='$rua', bairro='$bairro', cidade='$cidade', estado='$estado', latitude='$latitude', longitude='$longitude')"
    }
}
