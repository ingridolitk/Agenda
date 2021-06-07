package com.ingrid.myagenda

import android.widget.EditText

open class Pessoa(
    var nome: String,
    var celular: Int,
    var referencia: String,
    var email: String,
    var tipo: enumLista
) {
    fun exibirRegistro(): String {
        return this.nome
    }
}