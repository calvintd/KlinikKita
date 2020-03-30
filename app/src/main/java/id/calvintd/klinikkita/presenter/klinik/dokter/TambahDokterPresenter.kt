package id.calvintd.klinikkita.presenter.klinik.dokter

import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.klinik.dokter.TambahDokterView

class TambahDokterPresenter(private val tambahDokterView: TambahDokterView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val dokterRef = reference.child("dokter")

    fun tambahDokter(dokter: Dokter) {
        val key = dokterRef.push().key
        var terisi = true
        key?.let {
            if (dokter.namaDokter.isEmpty() || dokter.deskripsi.isEmpty()) {
                terisi = false
            }

            tambahDokterView.cekNama()
            tambahDokterView.cekDeskripsi()

            if (terisi) {
                dokterRef.child(it).setValue(dokter)
                tambahDokterView.tambahDokter()
            }
        }
    }
}