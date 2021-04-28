package br.ufpe.cin.android.doguinhohero

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.ufpe.cin.android.doguinhohero.data2.User
import br.ufpe.cin.android.doguinhohero.data2.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //actionbar
        supportActionBar!!.title = "Doguinho Hero"

        var btnLogin = findViewById<Button>(R.id.loginButton)
        btnLogin.setOnClickListener(this)

        var cadastroTextView = findViewById<TextView>(R.id.cadastroText)
        cadastroTextView.setOnClickListener{

            val intent = Intent(this, CadastroUsuarioActivity::class.java)
                    startActivity(intent)
        }

    }

    override fun onClick(v: View?) {
        // fazer login
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val email = etEmail.text.toString()

        try{
            val test = mUserViewModel.findUser(email)
            Log.d("usurio: ", test.toString())


            Toast.makeText(applicationContext,  "USUARIO ENCONTRADO!", Toast.LENGTH_LONG).show()

        } catch (ex: Exception) {
            Log.d("Erro: ", ex.toString())
        }



    }

}