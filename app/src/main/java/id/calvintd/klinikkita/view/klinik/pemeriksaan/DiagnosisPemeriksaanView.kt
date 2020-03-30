package id.calvintd.klinikkita.view.klinik.pemeriksaan

import id.calvintd.klinikkita.view.ErrorView

interface DiagnosisPemeriksaanView : ErrorView {
    fun cekDiagnosis()
    fun cekPengobatan()
    fun diagnosisSukses()
}