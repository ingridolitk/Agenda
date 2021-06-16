package com.ingrid.myagenda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultado: TextView
    private lateinit var edtPesquisar: EditText
    private lateinit var btnPesquisar: Button
    private lateinit var novaAgenda: Agenda
    private var selecionar: TipoContato = TipoContato.Pessoal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        novaAgenda = Agenda()
        configuraView()
        listenerFind()
        SalveOnClick()
    }

    fun configuraView() {
        resultado = findViewById(R.id.resultado)
        edtPesquisar = findViewById(R.id.edtPesquisar)
        btnPesquisar = findViewById(R.id.btnPesquisar)
    }

    private fun SalveOnClick() {

        val intent = Intent(this, SecondActivity_Cadastro::class.java)
        startActivity(intent)

        val it = intent
        val nome = it.getStringExtra("nome")
        val pessoa = Pessoa(
            selecionar.toString()
        )
        novaAgenda.cadastrarContato(pessoa)
        resultado.text = returnListString(novaAgenda.retonarLista())

    }

    private fun mostraMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    private fun listenerFind() {
        btnPesquisar.setOnClickListener {
            val pesquisa = edtPesquisar.text.toString()
            val listaResultados = mutableListOf<Pessoa>()

            checaItensAgenda(pesquisa, listaResultados)

            checaResultado(listaResultados)
            Log.d("Agenda", "Passei por aqui. listenerPesquisa")
        }
    }

    private fun checaResultado(listaResultados: MutableList<Pessoa>) {
        if (listaResultados.isNotEmpty()) {
            resultado.text = returnListString(listaResultados)
        } else {
            mostraMensagem("Não foi possível encontrar um contato")
            resultado.text = returnListString(novaAgenda.retonarLista())
            Log.d("Agenda", "Passei por aqui. checaResultado")
        }
    }
    private fun checaItensAgenda(
        pesquisa: String,
        listaResultados: MutableList<Pessoa>
    ) {
        novaAgenda.retonarLista().forEach { pessoa ->
            if (pessoa.nome.contains(pesquisa)) {
                listaResultados.add(pessoa)
            }
        }
    }

    fun returnListString(mutableList: MutableList<Pessoa>): String {
        var texto = ""
        mutableList.sortBy { it.nome }
        for (pessoa in mutableList) {
            when (pessoa.tipo) {
                TipoContato.Trabalho -> {
                    texto += "Nome: " + pessoa.nome + " Celular: " + pessoa.celular + " Email " + pessoa.email + "\n"
                }
                TipoContato.Pessoal -> {
                    texto += "Nome: " + pessoa.nome + " Celular: " + pessoa.celular + " Referência " + pessoa.referencia + "\n"
                }
            }
        }
        return texto
    }
}