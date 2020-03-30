package id.calvintd.klinikkita.presenter.pasien.daftar

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.pasien.daftar.PencarianDokterView

class PencarianDokterPresenter(private val pencarianDokterView: PencarianDokterView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val dokterRef = reference.child("dokter")

    fun tampilDokter(idKlinik: String) {
        dokterRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                pencarianDokterView.error()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairDokter = mutableListOf<Pair<String, Dokter>>()

                for (snapshot in dataSnapshot.children) {
                    if (snapshot.child("idKlinik").getValue(String::class.java).equals(idKlinik)) {
                        val key = snapshot.key
                        val dokter = snapshot.getValue(Dokter::class.java)

                        if (key != null && dokter != null) pairDokter.add(Pair(key, dokter))
                    }
                }

                pencarianDokterView.tampilDokter(pairDokter)
            }
        })
    }
}