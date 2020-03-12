package id.calvintd.klinikkita.presenter

import android.widget.TextView
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.util.CekFormulirPendaftaran
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarPasienPresenter(val pendaftaranView: PendaftaranView) {
    val cek = CekFormulirPendaftaran

    fun cekFormulir(daftarKolom: List<Kolom>, nomorHP: String, txtNomorHP: TextView, password: String, passwordUlang: String,
                 txtPasswordUlang: TextView, persetujuan: Boolean, txtPersetujuan: TextView) : Boolean {
        var terverifikasi = false
        var terisi = true
        var terformat = true
        var bukanDuplikat = true
        var sesuaiPanjang = true
        var terulang = true
        var tersetujui = true

        for (kolom in daftarKolom) {
            if (!cek.kolomTerisi(kolom)) {
                if(terisi) terisi = false
                pendaftaranView.kolomKosong(kolom.kesalahan, kolom.pesan)
            }
        }

        if(nomorHP.isNotEmpty()) {
            if (CekFormulirPendaftaran.formatNomorHP(nomorHP)) {
                terformat = false
                pendaftaranView.nomorHPSalahFormat(txtNomorHP)
            } else if(CekFormulirPendaftaran.panjangNomorHP(nomorHP)) {
                sesuaiPanjang = false
                pendaftaranView.nomorHPTerlaluPendek(txtNomorHP)
            } else {
                pendaftaranView.sembunyikanTeksKesalahan(txtNomorHP)
            }
        }

        if(passwordUlang.isNotEmpty()) {
            if (CekFormulirPendaftaran.ulangKataSandi(password, passwordUlang)) {
                terulang = false
                pendaftaranView.passwordUlangSalah(txtPasswordUlang)
            } else {
                pendaftaranView.sembunyikanTeksKesalahan(txtPasswordUlang)
            }
        }

        if(!persetujuan) {
            tersetujui = false
            pendaftaranView.persetujuanDitolak(txtPersetujuan)
        } else {
            pendaftaranView.sembunyikanTeksKesalahan(txtPersetujuan)
        }

        if(terisi && terformat && sesuaiPanjang && bukanDuplikat && terulang && tersetujui) terverifikasi = true

        return terverifikasi
    }
}