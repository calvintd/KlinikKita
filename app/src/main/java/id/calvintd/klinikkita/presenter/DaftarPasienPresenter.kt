package id.calvintd.klinikkita.presenter

import android.widget.TextView
import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarPasienPresenter(
    private val pendaftaranView: PendaftaranView,
    private val dataPasien: Pasien
) {
    val nomorHP = dataPasien.nomorHP
    val kataSandi = dataPasien.password
    val pendaftaranPresenter = PendaftaranPresenter(pendaftaranView)

    fun cekFormulir(
        daftarKolom: List<Kolom>,
        txtNomorHP: TextView,
        kataSandiUlang: String,
        txtKataSandiUlang: TextView,
        persetujuan: Boolean,
        txtPersetujuan: TextView
    ): Boolean {
        var sukses = false

        val terverifikasi = pendaftaranPresenter.verifikasiData(
            daftarKolom,
            nomorHP,
            txtNomorHP,
            kataSandi,
            kataSandiUlang,
            txtKataSandiUlang,
            persetujuan,
            txtPersetujuan
        )
        var bukanDuplikat = true

        if (terverifikasi && bukanDuplikat) sukses = true

        return sukses
    }

    fun cekDuplikatNomorHPPasien() {

    }
}