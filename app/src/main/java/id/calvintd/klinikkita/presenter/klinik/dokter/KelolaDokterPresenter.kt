package id.calvintd.klinikkita.presenter.klinik.dokter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.view.klinik.dokter.KelolaDokterView

class KelolaDokterPresenter(private val kelolaDokterView: KelolaDokterView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val dokterRef = reference.child("dokter")

    fun cekDokterKlinik(idKlinik: String) {
        dokterRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                kelolaDokterView.error()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listKeyDokter = mutableListOf<String>()
                val listDokter = mutableListOf<Dokter>()

                for (snapshot in dataSnapshot.children) {
                    if (snapshot.child("idKlinik").getValue(String::class.java).equals(idKlinik)) {
                        snapshot.key?.let { listKeyDokter.add(it) }
                        snapshot.getValue(Dokter::class.java)?.let { listDokter.add(it) }
                    }
                }

                if (listKeyDokter.isEmpty()) {
                    kelolaDokterView.daftarDokterKosong()
                } else {
                    kelolaDokterView.isiDaftarDokter(listKeyDokter, listDokter)
                }
            }

        })
    }

    fun hapusDokterKlinik(idDokter: String) {
        dokterRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                kelolaDokterView.error()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key.equals(idDokter)) {
                        dokterRef.child(idDokter).removeValue()
                        kelolaDokterView.dokterTerhapus()
                    }
                }
            }
        })
    }
}