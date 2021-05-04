package br.ufpe.cin.android.doguinhoHero

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import br.ufpe.cin.android.doguinhoHero.data2.cartao.Cartao
import br.ufpe.cin.android.doguinhoHero.data2.cartao.CartaoViewModel
import br.ufpe.cin.android.doguinhoHero.utils.getToastMessage
import br.ufpe.cin.android.doguinhoHero.utils.verifyInputAndGetText
import br.ufpe.cin.android.doguinhoHero.utils.verifySpinnerAndGetSelectedOption
import kotlinx.android.synthetic.main.activity_cadastro_cartao_de_credito.*

class CadastroCartaoDeCreditoActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mCartaoViewModel: CartaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_cartao_de_credito)

        //actionbar
        val actionbar = supportActionBar
        //definindo o titulo da actionbar
        actionbar!!.title = "Cadastro Doguinho Hero"

        startSpinerInput(R.id.spFormaDePagamento, arrayOf("Escolha um tipo", "Crédito", "Débito"))

        var nextStep = findViewById<Button>(R.id.btnFinalizarCadastro)
        nextStep.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val camposFaltantes = StringBuilder()

        var formaDePagamento = verifySpinnerAndGetSelectedOption(spFormaDePagamento,"@string/forma_de_pagamento", camposFaltantes)
        var numeroDoCartao = verifyInputAndGetText(etNumeroCartao, "@string/numero_do_cart_o", camposFaltantes)
        var nomeDoTitular = verifyInputAndGetText(spNomeDoTitular, "@string/nome_do_titular", camposFaltantes)
        var dataDeVencimento = verifyInputAndGetText(etDataDeVencimento, "@string/data_de_vencimento", camposFaltantes)
        var codigoDeSeguranca = verifyInputAndGetText(etCodigoDeSeguranca, "@string/codigo_de_seguranca", camposFaltantes)

        // Verificar se todos os campos estão preenchidos
        if(camposFaltantes.toString() != ""){
            var toastText = getToastMessage(camposFaltantes)
            Toast.makeText(applicationContext,  toastText, Toast.LENGTH_SHORT).show()
        } else {

            // Caso todos os campos estejam preenchidos o cartao é cadastrado

            //registra a conta bancaria
            mCartaoViewModel = ViewModelProvider(this).get(CartaoViewModel::class.java)

            val cartao = Cartao(0, formaDePagamento, numeroDoCartao, nomeDoTitular, dataDeVencimento, codigoDeSeguranca)

            // bug não está retornando o id
            var idNewCartao = mCartaoViewModel.addCartao(cartao)

            //insere os dados da conta bancaria no registro do pet hero
            var usuarioId = ""
            var extras = getIntent().getExtras();
            if (extras != null) {
                usuarioId = extras.getString("usuarioId").toString();
            }
            //TOOO

            // alerta
            alertaFinalizacao()
        }
    }

    fun startSpinerInput(spinnerId: Int, opcoes: Array<String>){
        // Estruturando o spinner de tipos de usuáios
        var userTypesOptions = opcoes

        var spinner = findViewById<Spinner>(spinnerId)
        spinner!!.setOnItemSelectedListener(this)

        // Criando um ArrayAdapter usando um spiner simples e uma array de opções
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypesOptions)
        // Definindo o layout a ser usado quando a lista de opções aparecer
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Definir adaptador para girador
        spinner!!.setAdapter(aa)
    }

    fun alertaFinalizacao() {
        val msgBox = AlertDialog.Builder(this)
        msgBox.setTitle(resources.getString(R.string.title_cadastro_concluido))
        msgBox.setIcon(android.R.drawable.ic_dialog_info)
        msgBox.setMessage(resources.getString(R.string.msg_cadastro_concluido))
        msgBox.setPositiveButton("OK", DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
            val i = Intent(applicationContext, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.putExtra("EXIT", true)
            startActivity(i)
            finish()
        })
        msgBox.show()
    }

    // funções sem uso, só estão aqui porque o android studio obriga
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


}