package id.calvintd.klinikkita.view.klinik.pemeriksaan

import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran

interface KelolaPendaftaranView {
    fun cekPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>)
    fun pendaftaranKosong()
    fun olahDataPasien(pairPasien: List<Pair<String, String>>)
    fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>)
    fun olahDataKlinik(pairKlinik: List<Pair<String, String>>)
    fun tidakHadirPendaftaran()
}