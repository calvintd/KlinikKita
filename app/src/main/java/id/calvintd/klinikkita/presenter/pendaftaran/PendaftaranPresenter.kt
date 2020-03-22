package id.calvintd.klinikkita.presenter.pendaftaran

import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.util.CekFormulirPendaftaran
import id.calvintd.klinikkita.view.PendaftaranView

class PendaftaranPresenter(private val pendaftaranView: PendaftaranView) {
    private val cek = CekFormulirPendaftaran

    fun verifikasiData(
        daftarKolom: List<Kolom>,
        nomorHP: String,
        txtNomorHP: TextView,
        kataSandi: String,
        kataSandiUlang: String,
        txtKataSandiUlang: TextView,
        persetujuan: Boolean,
        txtPersetujuan: TextView
    ): Boolean {
        var terverifikasi = false
        var terisi = true
        var terformat = true
        var sesuaiPanjang = true
        var terulang = true

        if (!cek.kolomTerisi(daftarKolom)) {
            if (terisi) terisi = false
            for (kolom in daftarKolom) {
                if (kolom.isiKolom.isEmpty()) pendaftaranView.kolomKosong(
                    kolom.teksKesalahan,
                    kolom.pesan
                )
            }
        } else {
            for (kolom in daftarKolom) sembunyikan(kolom.teksKesalahan)
        }

        if (nomorHP.isNotEmpty()) {
            if (!cek.formatNomorHP(nomorHP)) {
                terformat = false
                pendaftaranView.nomorHPSalahFormat(txtNomorHP)
            } else if (!cek.panjangNomorHP(nomorHP)) {
                sesuaiPanjang = false
                pendaftaranView.nomorHPTerlaluPendek(txtNomorHP)
            } else {
                sembunyikan(txtNomorHP)
            }
        }

        if (kataSandiUlang.isNotEmpty()) {
            if (!cek.ulangKataSandi(kataSandi, kataSandiUlang)) {
                terulang = false
                pendaftaranView.passwordUlangSalah(txtKataSandiUlang)
            } else {
                sembunyikan(txtKataSandiUlang)
            }
        }

        if (!persetujuan) {
            pendaftaranView.persetujuanDitolak(txtPersetujuan)
        } else {
            sembunyikan(txtPersetujuan)
        }

        if (terisi && terformat && sesuaiPanjang && terulang && persetujuan) terverifikasi = true

        return terverifikasi
    }

    private fun sembunyikan(teksKesalahan: TextView) {
        pendaftaranView.sembunyikanTeksKesalahan(teksKesalahan)
    }
}