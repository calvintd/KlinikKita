package id.calvintd.klinikkita.view.klinik.dokter

import id.calvintd.klinikkita.itemmodel.database.Dokter

interface KelolaDokterView {
    fun daftarDokterKosong()
    fun isiDaftarDokter(listKeyDokter: List<String>, listDokter: List<Dokter>)
    fun dokterTerhapus()
}