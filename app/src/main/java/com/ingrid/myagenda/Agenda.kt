package com.ingrid.myagenda

class Agenda {
    var lista: MutableList<Pessoa> = mutableListOf()

    fun cadastrarContato(pessoa: String) {
        this.lista.add(pessoa:Pessoa)
    }

    fun retonarLista(): MutableList<Pessoa> {
        return this.lista
    }
}

