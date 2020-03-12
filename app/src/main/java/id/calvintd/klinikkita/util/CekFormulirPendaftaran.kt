package id.calvintd.klinikkita.util

import android.view.View
import android.widget.TextView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.Kolom

object CekFormulirPendaftaran {
    fun kolomTerisi(kolom: Kolom) : Boolean {
        val terisi: Boolean
        val isiKolom = kolom.isiKolom

        terisi = isiKolom.text.isNotEmpty()

        return terisi
    }

    fun formatNomorHP(nomorHP: String) : Boolean {
        return nomorHP.startsWith("08")
    }

    fun panjangNomorHP(nomorHP: String) : Boolean {
        return nomorHP.length >= 10
    }

    fun ulangKataSandi(password: String, passwordUlang: String) : Boolean {
        return passwordUlang.contentEquals(password)
    }
}