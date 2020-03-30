package id.calvintd.klinikkita.presenter.pasien.daftar

import com.google.firebase.database.FirebaseDatabase
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.view.pasien.daftar.EntriKeluhanView

class EntriKeluhanPresenter(private val entriKeluhanView: EntriKeluhanView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pendaftaranRef = reference.child("pendaftaran")

    fun entriKeluhan(idPasien: String, idDokter: String, keluhan: String) {
        val pendaftaran = Pendaftaran(idPasien, idDokter, keluhan, 0)
        val key = pendaftaranRef.push().key
        key?.let {
            pendaftaranRef.child(it).setValue(pendaftaran)
            entriKeluhanView.entriKeluhan()
        }
    }
}