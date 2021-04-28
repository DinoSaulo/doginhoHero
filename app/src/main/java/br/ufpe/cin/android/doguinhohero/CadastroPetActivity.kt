package br.ufpe.cin.android.doguinhohero

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import br.ufpe.cin.android.doguinhohero.utils.getToastMessage
import br.ufpe.cin.android.doguinhohero.utils.verifyInputAndGetText
import br.ufpe.cin.android.doguinhohero.utils.verifySpinnerAndGetSelectedOption
import kotlinx.android.synthetic.main.activity_cadastro_pet.*

class CadastroPetActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_pet)

        //actionbar
        val actionbar = supportActionBar
        //definindo o titulo da actionbar
        actionbar!!.title = "Cadastro Doguinho Hero"

        startSpinerInput(R.id.spTipoDePet, arrayOf("Escolha um tipo", "Cachorro", "Gato"))
        startSpinerInput(R.id.spPorteDoPet, arrayOf("Escolha um porte", "Gigante", "Grande", "Médio", "Pequeno", "Muito Pequeno"))

        var nextStep = findViewById<Button>(R.id.nextStepButton2)
        nextStep.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val camposFaltantes = StringBuilder()

        var tipoInput = verifySpinnerAndGetSelectedOption(spTipoDePet, "Tipo do PET", camposFaltantes)
        var nameInput = verifyInputAndGetText(etNomeDoPet, "Nome", camposFaltantes)
        var porteInput = verifySpinnerAndGetSelectedOption(spPorteDoPet, "Porte", camposFaltantes)
        var idadeInput = verifyInputAndGetText(etIdadeDoPet, "Idade", camposFaltantes)
        // verificar também a foto

        // Verificar se todos os campos estão preenchidos
        if(camposFaltantes.toString() != ""){
            var toastText = getToastMessage(camposFaltantes)
            Toast.makeText(applicationContext,  toastText, Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, CadastroCartaoDeCreditoActivity::class.java)
            startActivity(intent)
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

    // funções sem uso, só estão aqui porque o android studio obriga
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}