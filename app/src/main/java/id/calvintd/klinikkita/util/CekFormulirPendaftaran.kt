package id.calvintd.klinikkita.util

import android.view.View
import android.widget.EditText
import id.calvintd.klinikkita.itemmodel.internal.Kolom

object CekFormulirPendaftaran {
    fun kolomTerisi(kolom: Kolom) : Boolean {
        val terisi: Boolean
        val isiKolom = kolom.isiKolom
        val kesalahan = kolom.kesalahan
        val pesan = kolom.pesan

        if(isiKolom.text.isEmpty()) {
            terisi = false
            kesalahan.visibility = View.VISIBLE
            kesalahan.text = pesan
        } else {
            terisi = true
            kesalahan.visibility = View.GONE
        }

        return terisi
    }

   fun formatNomorHP(nomorHP: String) : Boolean {
        return nomorHP.startsWith("08")
    }

    fun panjangNomorHP(nomorHP: String) : Boolean {
        return nomorHP.length >= 10
    }

    fun ulangKataSandi(password: EditText, passwordUlang: EditText) : Boolean {
        val txtPassword = password.text.toString()
        val txtPasswordUlang = passwordUlang.text.toString()
        return txtPasswordUlang.contentEquals(txtPassword)
    }
}