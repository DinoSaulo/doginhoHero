package br.ufpe.cin.android.doguinhoHero.utils

import android.graphics.Color
import android.widget.*

class utils {

}

fun verifyInputAndGetText(textView: EditText, nomeInput: String, camposFaltantes: StringBuilder): String{
    var red = "#ffb3b3"

    val input = textView
    val inputValue = input.text.toString()

    if(inputValue == ""){
        camposFaltantes.append("$nomeInput, ");
        input.setBackgroundColor(Color.parseColor(red))
    } else {
        input.setBackgroundColor(Color.TRANSPARENT)
    }

    return inputValue
}

fun verifySpinnerAndGetSelectedOption(spinner: Spinner, nomeSpinner: String, camposFaltantes: StringBuilder): String{
    var red = "#ffb3b3"

    val input = spinner
    val inputValue = input.getSelectedItem().toString()

    if(inputValue == "Escolha um tipo" ||inputValue == "Escolha um porte" ){
        camposFaltantes.append("$nomeSpinner, ");
        input.setBackgroundColor(Color.parseColor(red))
    } else {
        input.setBackgroundColor(Color.TRANSPARENT)
    }

    return inputValue
}

fun getQuantidadeCamposFaltando(camposFaltantes: StringBuilder): Int{
    return camposFaltantes.count{ c -> c == ',' }
}

fun getToastMessage(camposFaltantes: StringBuilder ): String{

    var qtdCamposFaltando = camposFaltantes.count{ c -> c == ',' }

    val consoantePlural = if(qtdCamposFaltando > 1) "s" else ""

    var complementoToast = camposFaltantes.dropLast(2).toString().replace("(.*), (.*)".toRegex(), "$1 e $2")

    var toastText = "Preencha o$consoantePlural campo$consoantePlural: $complementoToast"

    return toastText
}

fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun startSpinerInput(spinner: Spinner, opcoes: Array<String>){
    // Estruturando o spinner de tipos de usuáios
    var userTypesOptions = opcoes

    var spinner = spinner
    //spinner!!.setOnItemSelectedListener(this)

    // Criando um ArrayAdapter usando um spiner simples e uma array de opções
    //val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypesOptions)
    // Definindo o layout a ser usado quando a lista de opções aparecer
    //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    // Definir adaptador para girador
    //spinner!!.setAdapter(aa)
}



