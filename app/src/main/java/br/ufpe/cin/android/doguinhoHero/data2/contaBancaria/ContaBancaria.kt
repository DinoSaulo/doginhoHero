package br.ufpe.cin.android.doguinhoHero.data2.contaBancaria

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contas_bancarias")
data class ContaBancaria (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val contaBancariaId: Long = 0,
    /*@Entity(foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE)))
    val userId: Long = 0,*/
    val tipoDeConta: String,
    val banco: String,
    val agencia: String,
    val conta: String

)