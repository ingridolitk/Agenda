package com.ingrid.myagenda

class Agenda() {
     var lista:MutableList<Pessoa> = mutableListOf<Pessoa>()

    fun cadastrarContato(pessoa2: Pessoa){
        this.lista.add(pessoa2)
    }
    fun retornaContatos(): MutableList<Pessoa> {
        return this.lista
    }
    fun retonarLista(): MutableList<Pessoa>  {
        return this.lista
    }
}

