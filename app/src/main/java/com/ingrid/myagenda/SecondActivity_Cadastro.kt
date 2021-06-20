package com.ingrid.myagenda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class SecondActivity_Cadastro : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var cel: EditText
    private lateinit var ref: EditText
    private lateinit var email: EditText
    private lateinit var salvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_cadastro)
        configuraView()
        salveOnClick()
    }
    fun configuraView() {
        nome = findViewById(R.id.nome)
        cel = findViewById(R.id.celular)
        ref = findViewById(R.id.referencia)
        email = findViewById(R.id.email)
        salvar = findViewById(R.id.salvar)

    }
    private fun salveOnClick() {

        salvar.setOnClickListener() {
            val pessoa = arrayOf( Pessoa(
                nome.text.toString(),
                cel.toString(),
                email.text.toString(),
                ref.text.toString(),
                TipoContato.Pessoal))

            if (nome.text.toString().isEmpty() || cel.text.toString().isEmpty()) {
               nome.error = "Campo obrigatório"
            }
            else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(CONTATOS_KEY, pessoa)
//                intent.putExtra("nome", pessoa.nome)
//                intent.putExtra("cel", cel)
//                intent.putExtra("email", email)
//                intent.putExtra("referencia", ref)
                 startActivity(intent)
               // agenda.cadastrarContato(pessoa)
                //finish()
            }
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked
            when (view.id) {
                R.id.btnPessoal ->
                    if (foiChecado) {
                        var tipo = TipoContato.Pessoal
                        email.visibility = View.GONE
                        ref.visibility = View.VISIBLE
                    }
                R.id.btnTrabalho -> {
                    if (foiChecado) {
                       var tipo = TipoContato.Trabalho
                        email.visibility = View.VISIBLE
                        ref.visibility = View.GONE
                    }
                }
            }
        }
    }
    companion object{
        val CONTATOS_KEY = "PESSOA"
    }
}
