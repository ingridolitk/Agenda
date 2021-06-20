package com.ingrid.myagenda

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Pessoa(
    var nome: String,
    var celular: String,
    var referencia: String,
    var email: String,
    var tipo: TipoContato
): Parcelable {
    fun Pessoa2(nome:String, celular: String,referencia: String, email: String, tipo: TipoContato ){
        this.nome=nome
        this.celular=celular
        this.referencia=referencia
        this.email=email
        this.tipo=tipo
    }

    override fun toString(): String {
        return nome
    }
}