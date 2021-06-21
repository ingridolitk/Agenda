package com.ingrid.myagenda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ingrid.myagenda.R.id
import com.ingrid.myagenda.SecondActivity_Cadastro.Companion.CONTATOS_KEY

class MainActivity : AppCompatActivity() {
    private lateinit var resultado: TextView
    private lateinit var edtPesquisar: EditText
    private lateinit var btnPesquisar: Button
    private lateinit var novaAgenda: Agenda
    private lateinit var btnAcessa: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configuraView()
        novaAgenda = Agenda()

        listenerPesquisa()

        val intent = Intent(this, SecondActivity_Cadastro::class.java)

        btnAcessa.setOnClickListener() {
            val intent = Intent(this, SecondActivity_Cadastro::class.java)
            startActivity(intent)
        }
//
//        if(!arrayContatos.toString().isNullOrEmpty())
//            resultado.text = arrayContatos.toString()

    }

    override fun onResume() {
        super.onResume()
        val arrayContato = intent.getParcelableArrayExtra(CONTATOS_KEY)
        if (arrayContato != null) {
            //  val contato = arrayContato[it] as Pessoa
            arrayContato.forEach {
                val contato = arrayContato[0] as Pessoa
                resultado.text =
                    contato.nome + contato.celular + contato.referencia
                Toast.makeText(this, contato.nome, Toast.LENGTH_SHORT).show()
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

            checaItensAgenda(pesquisa, listaResultados)

            checaResultado(listaResultados)
        }
    }

    private fun checaResultado(listaResultados: MutableList<Pessoa>) {
        if (listaResultados.isNotEmpty()) {
            resultado.text = novaAgenda.exibirLista(listaResultados)
        } else {
            mostraMensagem("Não foi possível encontrar um contato")
            resultado.text = novaAgenda.exibirLista(novaAgenda.retonarLista())
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

    private fun configuraView() {
        resultado = findViewById(id.resultado)
        edtPesquisar = findViewById(id.edtPesquisar)
        btnPesquisar = findViewById(id.btnPesquisar)
        btnAcessa = findViewById(id.btnAcessa)
    }
}

private operator fun <T> Array<T>.get(it: T): T {
    return it
}