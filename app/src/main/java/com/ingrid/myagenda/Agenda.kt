package com.ingrid.myagenda

class Agenda {
    var lista: MutableList<Pessoa> = mutableListOf()

    fun cadastrarContato(pessoa: Pessoa) {
        this.lista.add(pessoa)
    }

    fun retonarLista(): MutableList<Pessoa> {
        return this.lista
    }
    fun exibirLista(mutableList: MutableList<Pessoa>):String {
        var texto = ""
        mutableList.sortBy { it.nome }
        for (pessoa in mutableList) {
            when (pessoa.tipo) {
                TipoContato.Trabalho -> {
                    texto += "Nome: " + pessoa.nome + "Celular: " + pessoa.celular + " Email " + pessoa.email + "\n"
                }
                TipoContato.Pessoal -> {
                    texto += "Nome: " + pessoa.nome + "Celular: " + pessoa.celular + " Referência " + pessoa.referencia + "\n"
                }
            }
        }
        return texto
    }
}

