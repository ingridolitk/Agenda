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
}