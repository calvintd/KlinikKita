package id.calvintd.klinikkita.view

import android.widget.TextView

interface PendaftaranView {
    fun kolomKosong(teksKesalahan: TextView, teks: String)
    fun nomorHPSalahFormat(teksKesalahan: TextView)
    fun nomorHPTerlaluPendek(teksKesalahan: TextView)
    fun nomorHPTerdaftar(teksKesalahan: TextView)
    fun passwordUlangSalah(teksKesalahan: TextView)
    fun persetujuanDitolak(teksKesalahan: TextView)
    fun setelTeksKesalahan(teksKesalahan: TextView, teks: String)
    fun sembunyikanTeksKesalahan(teksKesalahan: TextView)
}