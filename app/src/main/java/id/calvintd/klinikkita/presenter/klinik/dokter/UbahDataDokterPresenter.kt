package id.calvintd.klinikkita.presenter.klinik.dokter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.klinik.dokter.UbahDataDokterView

class UbahDataDokterPresenter(private val ubahDataDokterView: UbahDataDokterView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val dokterRef = reference.child("dokter")

    fun ubahDataDokter(key: String, dokter: Dokter) {
        var terisi = true

        if (dokter.namaDokter.isEmpty() || dokter.deskripsi.isEmpty()) {
            terisi = false
        }

        ubahDataDokterView.cekNama()
        ubahDataDokterView.cekDeskripsi()

        if (terisi) {
            dokterRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.key == key) {
                            dokterRef.child(key).setValue(dokter)
                            ubahDataDokterView.ubahDataDokter()
                        }
                    }
                }
            })
        }
    }
}