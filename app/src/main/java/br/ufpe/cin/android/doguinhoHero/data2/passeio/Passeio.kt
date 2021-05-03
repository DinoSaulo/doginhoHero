package br.ufpe.cin.android.doguinhoHero.data2.passeio

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
//import java.sql.Date

@Entity(tableName = "passeios")
data class Passeio (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Long = 0,
    val localNome: String,
    val localLatitude: Double,
    val localLongitude: Double,
    val petId: Long,
    val donoDoPetId: Long,
    val petHeroId: Long,
    val valor: Double
    //val data: Date

)