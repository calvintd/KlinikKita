package id.calvintd.klinikkita.util

import id.calvintd.klinikkita.itemmodel.internal.Kolom

object CekFormulirPendaftaran {
    fun kolomTerisi(daftarKolom: List<Kolom>) : Boolean {
        var terisi = true

        for(kolom in daftarKolom) {
            if (kolom.isiKolom.isEmpty()) {
                if (terisi) terisi = false
            }
        }

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