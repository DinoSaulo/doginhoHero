package br.ufpe.cin.android.doguinhoHero

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.ufpe.cin.android.doguinhoHero.data2.usuarios.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //actionbar
        supportActionBar!!.title = "Doguinho Hero"

        var btnLogin = findViewById<Button>(R.id.loginButton)
        btnLogin.setOnClickListener{

            //email e senha
            val email = etEmail.text.toString()
            val senha = etSenha.text.toString()

            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel.findUser(email)
            // val usuario = mUserViewModel.loginUser(email, senha)

            var usuario = false

            // se não encontrar o ususário irá mostrar um toast
            if(!usuario) {
                Toast.makeText(this,"Usuário ou email inválidos", Toast.LENGTH_LONG).show()
            } else {
                // login realizado com sucesso
            }
        }

        var cadastroTextView = findViewById<TextView>(R.id.cadastroText)
        cadastroTextView.setOnClickListener{

            val intent = Intent(this, CadastroUsuarioActivity::class.java)
                    startActivity(intent)
        }

    }


}