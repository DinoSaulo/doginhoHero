package br.ufpe.cin.android.doguinhoHero

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import br.ufpe.cin.android.doguinhoHero.data2.contaBancaria.ContaBancaria
import br.ufpe.cin.android.doguinhoHero.data2.contaBancaria.ContaBancariaViewModel
import br.ufpe.cin.android.doguinhoHero.utils.*
import kotlinx.android.synthetic.main.activity_cadastro_conta_bancaria.*

class CadastroContaBancariaActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mContaBancariaViewModel: ContaBancariaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_conta_bancaria)

        //actionbar
        val actionbar = supportActionBar
        //definindo o titulo da actionbar
        actionbar!!.title = "Cadastro Doguinho Hero"

        startSpinerInput(R.id.sptipoDeConta, arrayOf("Escolha um tipo de conta", "Conta Corrente", "Conta Poupança"))
        startSpinerInput(R.id.spBanco, arrayOf("Escolha um banco", "Banco do Brasil", "Caixa", "Itau", "Santander", "Outros"))

        var nextStep = findViewById<Button>(R.id.btnFinalizarCadastro)
        nextStep.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val camposFaltantes = StringBuilder()

        var banco = verifySpinnerAndGetSelectedOption(spBanco, "Banco", camposFaltantes)
        var tipoDeConta = verifySpinnerAndGetSelectedOption(sptipoDeConta, "Tipo de Conta", camposFaltantes)
        var agencia = verifyInputAndGetText(etAgencia, "Agência", camposFaltantes)
        var conta = verifyInputAndGetText(etConta, "Conta", camposFaltantes)

        // Verificar se todos os campos estão preenchidos
        if(camposFaltantes.toString() != ""){
            var toastText = getToastMessage(camposFaltantes)
            Toast.makeText(applicationContext,  toastText, Toast.LENGTH_SHORT).show()
        } else {
            // Caso todos os campos estejam preenchidos o cartao é cadastrado

            //registra a conta bancaria
            mContaBancariaViewModel = ViewModelProvider(this).get(ContaBancariaViewModel::class.java)

            val contaBancaria = ContaBancaria(0, tipoDeConta, banco, agencia, conta)

            // bug não está retornando o id
            var idNewContaBancaria = mContaBancariaViewModel.addContaBancaria(contaBancaria)

            //insere os dados da conta bancaria no registro do pet hero
            var usuarioId = ""
            var extras = getIntent().getExtras();
            if (extras != null) {
                usuarioId = extras.getString("usuarioId").toString();
            }
            //TOOO

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
        msgBox.setPositiveButton("OK", DialogInterface.OnClickListener(){ _: DialogInterface, i: Int ->
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