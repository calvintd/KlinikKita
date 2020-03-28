package id.calvintd.klinikkita.view

import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan

interface RiwayatPemeriksaanView {
    fun cekPemeriksaan(pairPemeriksaan: List<Pair<String, Pemeriksaan>>)
    fun pemeriksaanKosong()
    fun olahDataPendaftaran(pairPendaftaran: List<Pair<String, String>>)
    fun olahDataPasien(pairPasien: List<Pair<String, String>>)
    fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>)
    fun olahDataKlinik(pairKlinik: List<Pair<String, String>>)
}