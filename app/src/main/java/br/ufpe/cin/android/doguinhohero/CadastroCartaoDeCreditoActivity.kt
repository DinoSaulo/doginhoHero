package br.ufpe.cin.android.doguinhohero

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class CadastroCartaoDeCreditoActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
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

        var formaDePagamento = verifySpinnerAndGetSelectedOption(R.id.spFormaDePagamento, "Forma de pagamento", camposFaltantes)
        var numeroDoCartao = verifyInputAndGetText(R.id.etNumeroCartao, "Numero do Cartão", camposFaltantes)
        var nomeDoTitular = verifyInputAndGetText(R.id.spNomeDoTitular, "Nome do Titular", camposFaltantes)
        var dataDeVencimento = verifyInputAndGetText(R.id.etDataDeVencimento, "Data de Vencimento", camposFaltantes)
        var codigoDeSeguranca = verifyInputAndGetText(R.id.etCodigoDeSeguranca, "Código de Seguranca", camposFaltantes)

        var qtdCamposFaltando = getQuantidadeCamposFaltando(camposFaltantes)

        // Verificar se todos os campos estão preenchidos
        if(camposFaltantes.toString() != ""){
            val consoantePlural = if(qtdCamposFaltando > 1) "s" else ""

            var complementoToast = camposFaltantes.dropLast(2).toString().replace("(.*), (.*)".toRegex(), "$1 e $2")

            var toastText = "Preencha o$consoantePlural campo$consoantePlural: $complementoToast"

            Toast.makeText(applicationContext,  toastText, Toast.LENGTH_SHORT).show()
        } else {

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
        msgBox.setTitle("Cadastro realizado!")
        msgBox.setIcon(android.R.drawable.ic_dialog_info)
        msgBox.setMessage("Agora você já pode usar o Doguinho Hero. Aproveite!")
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

    fun verifyInputAndGetText(textView: Int, nomeInput: String, camposFaltantes: StringBuilder): String{
        var red = "#ffb3b3"

        val input = findViewById<EditText>(textView)
        val inputValue = input.text.toString()

        if(inputValue == ""){
            camposFaltantes.append(nomeInput + ", ");
            input.setBackgroundColor(Color.parseColor(red))
        } else {
            input.setBackgroundColor(Color.TRANSPARENT)
        }

        return inputValue

    }

    fun verifySpinnerAndGetSelectedOption(spinner: Int, nomeSpinner: String, camposFaltantes: StringBuilder): String{
        var red = "#ffb3b3"

        val input = findViewById<Spinner>(spinner)
        val inputValue = input.getSelectedItem().toString()

        if(inputValue == "Escolha um tipo" ||inputValue == "Escolha um porte" ){
            camposFaltantes.append(nomeSpinner + ", ");
            input.setBackgroundColor(Color.parseColor(red))
        } else {
            input.setBackgroundColor(Color.TRANSPARENT)
        }

        return inputValue
    }

    fun getQuantidadeCamposFaltando(camposFaltantes: StringBuilder): Int{
        return camposFaltantes.count{ c -> c == ',' }
    }

    // funções sem uso, só estão aqui porque o android studio obriga
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


}