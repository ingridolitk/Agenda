package com.ingrid.myagenda

open class Pessoa(
    var nome: String,
    var celular: Int,
    var referencia: String,
    var email: String,
    var tipo: TipoContato
) {
    fun exibirNome(): String {
        return this.nome
    }
}