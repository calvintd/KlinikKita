package id.calvintd.klinikkita.view.pasien.daftar

import id.calvintd.klinikkita.itemmodel.database.Klinik

interface PencarianKlinikView {
    fun dokterKlinikKosong()
    fun dokterTersedia(keyKlinikList: List<String>)
    fun tampilDaftarKlinik(pairKlinik: List<Pair<String, Klinik>>)
}