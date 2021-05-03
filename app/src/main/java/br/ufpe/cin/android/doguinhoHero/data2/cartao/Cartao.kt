package br.ufpe.cin.android.doguinhoHero.data2.cartao

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartoes")
data class Cartao (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val cartaoId: Long = 0,
    val formaDePagamento: String,
    val numceroCartao: String,
    val nomeTitular: String,
    val dataVencimento: String,
    val codigoSeguranca: String

)