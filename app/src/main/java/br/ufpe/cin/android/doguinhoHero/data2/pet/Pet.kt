package br.ufpe.cin.android.doguinhoHero.data2.pet

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pets")
data class Pet (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val petId: Long = 0,
    val nome: String,
    val porte: String,
    val idade: String,
    val tipo: String
    //val foto: String

)