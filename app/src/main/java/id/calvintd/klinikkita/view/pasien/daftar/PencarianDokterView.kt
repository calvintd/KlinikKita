package id.calvintd.klinikkita.view.pasien.daftar

import id.calvintd.klinikkita.itemmodel.database.Dokter

interface PencarianDokterView {
    fun tampilDokter(pairDokter: List<Pair<String, Dokter>>)
}