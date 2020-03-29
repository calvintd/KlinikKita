package id.calvintd.klinikkita.presenter.klinik.pemeriksaan

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.view.klinik.pemeriksaan.DiagnosisPemeriksaanView

class DiagnosisPemeriksaanPresenter(private val diagnosisPemeriksaanView: DiagnosisPemeriksaanView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pemeriksaanRef = reference.child("pemeriksaan")
    private val pendaftaranRef = reference.child("pendaftaran")

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
                ubahStatus(pemeriksaan.idPendaftaran)
                diagnosisPemeriksaanView.diagnosisSukses()
            }
        }
    }

    fun ubahStatus(idPendaftaran: String) {
        pendaftaranRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key.equals(idPendaftaran)) {
                        pendaftaranRef.child(idPendaftaran).child("status").setValue(1)
                        break
                    }
                }
            }
        })
    }
}