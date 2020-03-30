package id.calvintd.klinikkita.view.pasien.daftar

import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.ErrorView

interface PencarianDokterView : ErrorView {
    fun tampilDokter(pairDokter: List<Pair<String, Dokter>>)
}