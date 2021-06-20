package com.ingrid.myagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second_cadastro.*

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
            val pessoa = Pessoa(
                nome.text.toString(),
                cel.toString(),
                email.text.toString(),
                ref.text.toString(),
                tipo =TipoContato.Pessoal)
            if (nome.text.toString().isEmpty()) {
                mostraMensagem("O nome do usuário não foi inserido")
            }
            if (cel.text.toString().isEmpty()) {
                mostraMensagem("Informe o número de contato")
            }
            val intent = Intent(this, MainActivity::class.java)

            val listaContatos = arrayOf(
                Pessoa(nome="",celular = "", referencia = "", email="",tipo=TipoContato.Pessoal)
            )
            intent.putExtra(CONTATOS_KEY, listaContatos)
            intent.putExtra("nome", pessoa.nome)
            intent.putExtra("cel", pessoa.celular)
            intent.putExtra("email", pessoa.email)
            intent.putExtra("referencia", pessoa.referencia)
            startActivity(intent)
        }
    }
    private fun mostraMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
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
        val lista =  mutableListOf<String>("a", "b", "c")
    }
}
