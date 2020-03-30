package id.calvintd.klinikkita.view

interface PengaturanView : ErrorView {
    fun konfirmasi(kataSandiBaru: String)
    fun ubahKataSandi(kataSandiBaru: String)
    fun cekKolomPasswordLama()
    fun cekKolomPasswordBaru()
    fun cekKolomUlangPasswordBaru()
    fun cekPasswordLama(sukses: Boolean)
    fun cekUlangPasswordBaru(sukses: Boolean)
}