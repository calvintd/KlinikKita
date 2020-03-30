package id.calvintd.klinikkita.view.klinik.dokter

import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.ErrorView

interface KelolaDokterView : ErrorView {
    fun daftarDokterKosong()
    fun isiDaftarDokter(listKeyDokter: List<String>, listDokter: List<Dokter>)
    fun dokterTerhapus()
}