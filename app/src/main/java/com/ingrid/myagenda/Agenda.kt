package com.ingrid.myagenda

class Agenda {
    var lista: MutableList<Pessoa> = mutableListOf()

    fun cadastrarContato(pessoa: Pessoa) {
        this.lista.add(pessoa)
    }

    fun retonarLista(): MutableList<Pessoa> {
        return this.lista
    }
}

