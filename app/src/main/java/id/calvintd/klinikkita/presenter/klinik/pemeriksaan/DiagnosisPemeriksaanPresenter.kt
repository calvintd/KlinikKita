package id.calvintd.klinikkita.presenter.klinik.pemeriksaan

import com.google.firebase.database.FirebaseDatabase
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.view.klinik.pemeriksaan.DiagnosisPemeriksaanView

class DiagnosisPemeriksaanPresenter(private val diagnosisPemeriksaanView: DiagnosisPemeriksaanView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pemeriksaanRef = reference.child("pemeriksaan")

    fun diagnosis(pemeriksaan: Pemeriksaan) {
        val key = pemeriksaanRef.push().key
        var terisi = true
        key?.let {
            if (pemeriksaan.diagnosis.isEmpty() || pemeriksaan.pengobatan.isEmpty()) {
                terisi = false
            }

            diagnosisPemeriksaanView.cekDiagnosis()
            diagnosisPemeriksaanView.cekPengobatan()

            if (terisi) {
                pemeriksaanRef.child(it).setValue(pemeriksaan)
                diagnosisPemeriksaanView.diagnosisSukses()
            }
        }
    }
}