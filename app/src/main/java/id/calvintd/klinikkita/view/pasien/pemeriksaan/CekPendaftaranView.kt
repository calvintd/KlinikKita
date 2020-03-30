package id.calvintd.klinikkita.view.pasien.pemeriksaan

import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.view.ErrorView

interface CekPendaftaranView : ErrorView {
    fun pendaftaranKosong()
    fun tampilPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>)
    fun olahDataPasien(pairPasien: List<Pair<String, String>>)
    fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>)
    fun olahDataKlinik(pairKlinik: List<Pair<String, String>>)
}