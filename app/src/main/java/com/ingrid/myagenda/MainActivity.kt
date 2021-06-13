package com.ingrid.myagenda

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ingrid.myagenda.R.id

class MainActivity : AppCompatActivity() {
    private lateinit var nome: EditText
    private lateinit var cel: EditText
    private lateinit var ref: EditText
    private lateinit var email: EditText
    private lateinit var salvar: Button
    private lateinit var resultado: TextView
    private lateinit var edtPesquisar: EditText
    private lateinit var btnPesquisar: Button

    private lateinit var novaAgenda: Agenda

    private var selecionar: TipoContato = TipoContato.Pessoal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()
        configuraView()

        novaAgenda = Agenda()

        listenerPesquisa()
        botaoSalvar()
    }
    override fun onStart(){
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()
    }

    override fun onResume(){
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show()
    }
    private fun botaoSalvar() {
        salvar.setOnClickListener() {
            if (nome.text.toString().isEmpty())
                mostraMensagem("O nome do usuário não foi inserido")

            if (cel.text.toString().isEmpty()) {
                mostraMensagem("Informe o número de contato")

            } else {
                val pessoa = Pessoa(
                    nome.text.toString(),
                    cel.text.toString().toInt(),
                    email.text.toString(),
                    ref.text.toString(),
                    selecionar
                )
                novaAgenda.cadastrarContato(pessoa)
                resultado.text = retornarListaEmString(novaAgenda.retonarLista())

            }
        }
    }

    private fun mostraMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    private fun listenerPesquisa() {
        btnPesquisar.setOnClickListener {
            val pesquisa = edtPesquisar.text.toString()
            val listaResultados = mutableListOf<Pessoa>()

            checaItemsAgenda(pesquisa, listaResultados)

            checaResultado(listaResultados)
        }
    }

    private fun checaResultado(listaResultados: MutableList<Pessoa>) {
        if (listaResultados.isNotEmpty()) {
            resultado.text = retornarListaEmString(listaResultados)
        } else {
            mostraMensagem("Não foi possível encontrar um contato")
            resultado.text = retornarListaEmString(novaAgenda.retonarLista())
        }
    }

    private fun checaItemsAgenda(
        pesquisa: String,
        listaResultados: MutableList<Pessoa>
    ) {
        novaAgenda.retonarLista().forEach { pessoa ->
            if (pessoa.nome.contains(pesquisa)) {
                listaResultados.add(pessoa)
            }
        }
    }

    private fun configuraView() {
        nome = findViewById(id.nome)
        cel = findViewById(id.celular)
        ref = findViewById(id.referencia)
        email = findViewById(id.email)
        salvar = findViewById(id.salvar)
        resultado = findViewById(id.resultado)
        edtPesquisar = findViewById(id.edtPesquisar)
        btnPesquisar = findViewById(id.btnPesquisar)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked
            when (view.id) {
                id.btnPessoal ->
                    if (foiChecado) {
                        selecionar = TipoContato.Pessoal
                        email.visibility = View.GONE
                        ref.visibility = View.VISIBLE
                    }
                id.btnTrabalho -> {
                    if (foiChecado) {
                        selecionar = TipoContato.Trabalho
                        email.visibility = View.VISIBLE
                        ref.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun retornarListaEmString(mutableList: MutableList<Pessoa>): String {
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