package br.ufpe.cin.android.doguinhoHero

import android.content.DialogInterface
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.ufpe.cin.android.doguinhoHero.data2.usuarios.User
import br.ufpe.cin.android.doguinhoHero.data2.usuarios.UserViewModel
import br.ufpe.cin.android.doguinhoHero.utils.getToastMessage
import br.ufpe.cin.android.doguinhoHero.utils.verifyInputAndGetText
import br.ufpe.cin.android.doguinhoHero.utils.verifySpinnerAndGetSelectedOption
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class CadastroUsuarioActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)

        //actionbar
        val actionbar = supportActionBar
        //definindo o titulo da actionbar
        actionbar!!.title = "Cadastro Doguinho Hero"
        //setando o botão de voltar
        actionbar.setDisplayHomeAsUpEnabled(true)

        startSpinerInput()

        val etCEP = findViewById<EditText>(R.id.etCEP)
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        etCEP.addTextChangedListener(CadastroUsuarioActivity.MaskEditUtil.mask(etCEP,"#####-###"))
        btnSearch.setOnClickListener {
            if(etCEP.text.toString().length != 9){
                Toast.makeText(this,"O CEP precisa ter 8 Digitos", Toast.LENGTH_LONG).show()
            }
            else{
                getCep(etCEP.text.toString())
                getCoords(etCEP.text.toString())
            }
        }

        var nextStep = findViewById<Button>(R.id.nextStepButton)
        nextStep.setOnClickListener(this)

    }

    fun startSpinerInput(){
        // Estruturando o spinner de tipos de usuáios
        var userTypesOptions = arrayOf("Escolha um tipo", "Dono de Pet", "Pet Hero")

        var spinner = findViewById<Spinner>(R.id.userType)
        spinner!!.setOnItemSelectedListener(this)

        // Criando um ArrayAdapter usando um spiner simples e uma array de opções
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypesOptions)
        // Definindo o layout a ser usado quando a lista de opções aparecer
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Definir adaptador para girador
        spinner!!.setAdapter(aa)
    }

    override fun onSupportNavigateUp(): Boolean {
        val msgBox = AlertDialog.Builder(this)
        msgBox.setTitle("Tem certeza que deseja retornar?")
        msgBox.setIcon(android.R.drawable.ic_menu_delete)
        msgBox.setMessage("Todos os dados serão perdidos")
        msgBox.setPositiveButton("Sim", DialogInterface.OnClickListener(){ _: DialogInterface, _: Int ->
            onBackPressed()
        })
        msgBox.setNegativeButton("Não", DialogInterface.OnClickListener(){ _: DialogInterface, _: Int ->
            // faz nada
        })
        msgBox.show()

        return true
    }

    override fun onClick(v: View?) {

        val camposFaltantes = StringBuilder()

        var nameInput = verifyInputAndGetText(nameInput, "Nome", camposFaltantes)
        var emailInput = verifyInputAndGetText(emailInput, "Email", camposFaltantes)
        var repetirEmailInput = verifyInputAndGetText(repetirEmailInput, "Repetir Email", camposFaltantes)
        var passwordInput = verifyInputAndGetText(passwordInput, "Senha", camposFaltantes)
        var repeatPasswordInput = verifyInputAndGetText(repeatPasswordInput, "Repetir Senha", camposFaltantes)
        var userType = verifySpinnerAndGetSelectedOption(userType, "Tipo de Usuário", camposFaltantes)
        var cepInput = verifyInputAndGetText(etCEP, "CEP", camposFaltantes)
        var numeroInput = verifyInputAndGetText(etNumero, "Numero", camposFaltantes)
        val estadoInput = tvEstado.text.toString()
        val longitudeInput = tvLon.text.toString()

        // Verificar se todos os campos estão preenchidos
        if(camposFaltantes.toString() != ""){
            var toastText = getToastMessage(camposFaltantes)

            Toast.makeText(applicationContext,  toastText, Toast.LENGTH_SHORT).show()
        } else {

            // Verificar se emails são iguais
            if(emailInput != repetirEmailInput) Toast.makeText(applicationContext,  "Os emails informados são diferentes!", Toast.LENGTH_SHORT).show()

            // Verificar se o email é valido
            else if(!isEmailValid(emailInput)) Toast.makeText(applicationContext,  "O email informado não é válido!", Toast.LENGTH_SHORT).show()

            // Verificar se email está sendo usado

            // Verificar se senhas são iguais
            else if(passwordInput != repeatPasswordInput) Toast.makeText(applicationContext,  "As senhas informadas são diferentes!", Toast.LENGTH_SHORT).show()

            else if(longitudeInput == "" || estadoInput == "") Toast.makeText(applicationContext,  "Por favor, efetue a busca do CEP!", Toast.LENGTH_SHORT).show()

            // próxima etapa de cadastro
            else{

                val tipoUsusario = findViewById<Spinner>(R.id.userType).selectedItem.toString()

                if(tipoUsusario == "Dono de Pet"){
                    val intent = Intent(this, CadastroPetActivity::class.java)
                    val usuarioId = criarUsuario()
                    intent.putExtra("usuarioId", usuarioId);
                    startActivity(intent)
                } else { // pet hero
                    val intent = Intent(this, CadastroContaBancariaActivity::class.java)
                    val usuarioId = criarUsuario()
                    intent.putExtra("usuarioId", usuarioId);
                    startActivity(intent)
                }

            }
        }

    }

    fun criarUsuario() : Long {
        val nome = nameInput.text.toString()
        val email = emailInput.text.toString()
        val senha = passwordInput.text.toString()
        val tipo = userType.selectedItem.toString()
        val cep = etCEP.text.toString()
        val numero = etNumero.text.toString()
        val rua = tvRua.text.toString()
        val bairro = tvBairro.text.toString()
        val cidade = tvCidade.text.toString()
        val estado = tvEstado.text.toString()
        val latitude = tvLat.text.toString()
        val longitude = tvLon.text.toString()

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val user = User(0, nome, email, senha, tipo, cep, numero, rua, bairro, cidade, estado, latitude, longitude)

        // bug não está retornando o id
        var idNewUser = mUserViewModel.addUser(user)

        // apenas para debug remover depois
        val idNewUser2: Long = 2

        return idNewUser2
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // funções sem uso, só estão aqui porque o android studio obriga
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


    fun getCoords(cep: String){
        val url= "https://nominatim.openstreetmap.org/search?q=$cep&limit=2&format=json"
        getCoodenatesAsyncTask().execute(url)
    }

    fun getCep(cep: String){
        val url= "https://viacep.com.br/ws/"+cep+"/json/"
        MyAsyncTask().execute(url)
    }

    /* Trecho de codigo inspirado no projeto CEP Kotlin do site UWare (https://uware.com.br/procurar-endereco-com-o-cep-em-kotlin/) */
    object MaskEditUtil {
        fun mask(ediTxt: EditText, mask: String): TextWatcher {
            return object : TextWatcher {
                var isUpdating: Boolean = false
                var old = ""
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str = unmask(s.toString())
                    var mascara = ""
                    if (isUpdating) {
                        old = str
                        isUpdating = false
                        return
                    }
                    var i = 0
                    for (m in mask.toCharArray()) {
                        if (m != '#' && str.length > old.length) {
                            mascara += m
                            continue
                        }
                        try {
                            mascara += str[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                    isUpdating = true
                    ediTxt.setText(mascara)
                    ediTxt.setSelection(mascara.length)
                }
            }
        }
        fun unmask(s: String): String {
            return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), ""
                ).replace("[ ]".toRegex(), "").replace("[:]".toRegex(), "").replace("[)]".toRegex(), "")
        }
    }

    inner class getCoodenatesAsyncTask: AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            //pbCep.visibility = View.VISIBLE
        }
        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(params[0])
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 7000
                var instring = ConvertStreamString(urlConnection.inputStream)
                publishProgress(instring)
            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
            return ""
        }
        override fun onProgressUpdate(vararg params: String?) {
            try {
                var json = JSONArray(params[0]).getJSONObject(0)

                val lat = json.getString("lat")
                val lon = json.getString("lon")

                tvLat.text = lat
                tvLon.text = lon

            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
        }
        fun ConvertStreamString(inputStream: InputStream): String {
            val reader = BufferedReader(inputStream.reader())
            val content = StringBuilder()
            var line = reader.readLine()
            try {
                while (line != null) {
                    content.append(line)
                    line = reader.readLine()
                }
            } finally {
                reader.close()
            }
            return content.toString()
        }
    }

    /* Trecho de codigo inspirado no projeto CEP Kotlin do site UWare (https://uware.com.br/procurar-endereco-com-o-cep-em-kotlin/) */
    inner class MyAsyncTask: AsyncTask<String, String, String>() {

        val pbCep = findViewById<ProgressBar>(R.id.pbCep)

        override fun onPreExecute() {
            pbCep.visibility = View.VISIBLE
        }
        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(params[0])
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 7000
                var instring = ConvertStreamString(urlConnection.inputStream)
                publishProgress(instring)
            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
            return ""
        }
        override fun onProgressUpdate(vararg params: String?) {
            try {
                var json = JSONObject(params[0])
                val cep = json.getString("cep")
                val logradouro = json.getString("logradouro")
                val bairro = json.getString("bairro")
                val cidade = json.getString("localidade")
                val estado = json.getString("uf")
                pbCep.visibility = View.INVISIBLE

                tvRua.text = logradouro
                tvBairro.text = bairro
                tvCidade.text = cidade
                tvEstado.text = estado

            } catch (ex: Exception) {
                Log.d("Erro: ", ex.toString())
            }
        }
        fun ConvertStreamString(inputStream: InputStream): String {
            val reader = BufferedReader(inputStream.reader())
            val content = StringBuilder()
            var line = reader.readLine()
            try {
                while (line != null) {
                    content.append(line)
                    line = reader.readLine()
                }
            } finally {
                reader.close()
            }
            return content.toString()
        }
    }

}

private fun String.append(s: String) {

}
