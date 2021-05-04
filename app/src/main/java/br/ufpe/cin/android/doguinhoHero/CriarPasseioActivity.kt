package br.ufpe.cin.android.doguinhoHero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CriarPasseioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_passeio)

        //actionbar
        supportActionBar!!.title = "Doguinho Hero"
    }
}