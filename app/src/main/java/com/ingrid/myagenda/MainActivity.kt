package com.ingrid.myagenda

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nome:EditText
    private lateinit var cel:EditText
    private lateinit var ref :EditText
    private lateinit var email:EditText
    private lateinit var salvar:Button
    private lateinit var resultado :TextView
    private lateinit var edtPesquisar: EditText

    var selecionar: enumLista = enumLista.Pessoal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Minha Agenda")
         nome = findViewById<EditText>(R.id.nome)
         cel = findViewById<EditText>(R.id.celular)
         ref = findViewById<EditText>(R.id.referencia)
         email = findViewById<EditText>(R.id.email)
         salvar = findViewById<Button>(R.id.salvar)
         resultado = findViewById<TextView>(R.id.resultado)
         edtPesquisar = findViewById<EditText>(R.id.edtPesquisar)
        val novaAgenda = Agenda()
        val btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        var lista: enumLista? = null

        email.visibility = View.GONE
        ref.visibility = View.GONE

        btnPesquisar.setOnClickListener {
            val pesquisa = edtPesquisar.text.toString()
            var listPessoas = novaAgenda.retonarLista()
            val resultado2 = listPessoas.find { pessoa ->
                pessoa.nome == pesquisa
            }
            if (resultado != null) {
                resultado.text = resultado2!!.exibirRegistro()
            } else {
                Toast.makeText(
                    this,
                    "Não foi possível encontrar um funcionário",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        salvar.setOnClickListener() {
            nome = findViewById<EditText>(R.id.nome)

            if (nome.text.toString().isEmpty())
                Toast.makeText(this, "O nome do usuário não foi inserido", Toast.LENGTH_SHORT)
                    .show()
                    .toString()
            if (cel.text.toString().isEmpty()) {
                Toast.makeText(this, "Informe o número de contato", Toast.LENGTH_SHORT).show()
                    .toString()
            } else {
                var pessoa = Pessoa(nome.text.toString(), cel.text.toString().toInt(), email.text.toString(), ref.text.toString(), selecionar)
                var contatos = novaAgenda.cadastrarContato(pessoa)
                var retList = novaAgenda.retonarLista()
                var txt = retornarLista(retList)
                resultado.text = txt

            }
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked
            when (view.id) {
                R.id.btnPessoal ->
                    if (foiChecado) {
                        selecionar = enumLista.Pessoal
                        email.visibility = View.GONE
                        ref.visibility = View.VISIBLE


                    }
                R.id.btnTrabalho -> {
                    if (foiChecado) {
                        selecionar = enumLista.Trabalho
                        email.visibility = View.VISIBLE
                        ref.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun retornarLista(mutableList: MutableList<Pessoa>): String {
        var texto: String = ""
        mutableList.sortBy { it.nome }
        for (pessoa in mutableList) {
            if (selecionar == enumLista.Pessoal) {
                texto += "Nome: " + pessoa.nome + "Celular: " + pessoa.celular + " Referência " + pessoa.referencia  + "\n"

            }
            if (selecionar == enumLista.Trabalho) {
                texto += "Nome: " + pessoa.nome + "Celular: " + pessoa.celular + " E-mail " + pessoa.email +  "\n"
            }
        }
        return texto
    }
}