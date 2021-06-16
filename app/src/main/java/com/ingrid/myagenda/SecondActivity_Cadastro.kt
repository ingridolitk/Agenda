package com.ingrid.myagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast

class SecondActivity_Cadastro : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var cel: EditText
    private lateinit var ref: EditText
    private lateinit var email: EditText
    private lateinit var salvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_cadastro)
        SalveOnClick()
    }
    fun configuraView() {
        nome = findViewById(R.id.nome)
        cel = findViewById(R.id.celular)
        ref = findViewById(R.id.referencia)
        email = findViewById(R.id.email)
        salvar = findViewById(R.id.salvar)

    }
    private fun SalveOnClick() {
        configuraView()
        val pessoa = Pessoa(
            nome.text.toString(),
            cel.toString(),
            email.text.toString(),
            ref.text.toString(),
            tipo =TipoContato.Pessoal)

        salvar.setOnClickListener() {
            if (nome.text.toString().isEmpty()) {
                mostraMensagem("O nome do usuário não foi inserido")
            }
            if (cel.text.toString().isEmpty()) {
                mostraMensagem("Informe o número de contato")
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nome", pessoa.nome)
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


}
