package id.calvintd.klinikkita.presenter

import android.widget.TextView
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarKlinikPresenter(
    private val pendaftaranView: PendaftaranView,
    dataKlinik: Klinik
) {
    val nomorHP = dataKlinik.nomorHP
    val kataSandi = dataKlinik.password
    val pendaftaranPresenter = PendaftaranPresenter(pendaftaranView)

    fun cekFormulir(
        daftarKolom: List<Kolom>,
        txtNomorHP: TextView,
        kataSandiUlang: String,
        txtKataSandiUlang: TextView,
        persetujuan: Boolean,
        txtPersetujuan: TextView
    ) {
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

        if (terverifikasi) {
            if (!bukanDuplikat) {
                pendaftaranView.nomorHPTerdaftar(txtNomorHP)
            } else {

            }
        }
    }

    fun cekDuplikatNomorHPKlinik() {

    }
}