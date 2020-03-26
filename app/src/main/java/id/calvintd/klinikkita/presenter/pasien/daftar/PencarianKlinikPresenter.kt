package id.calvintd.klinikkita.presenter.pasien.daftar

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.view.pasien.daftar.PencarianKlinikView

class PencarianKlinikPresenter(private val pencarianKlinikView: PencarianKlinikView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val dokterRef = reference.child("dokter")
    private val klinikRef = reference.child("klinik")

    fun cekDokter() {
        dokterRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val keyKlinikList = mutableListOf<String>()

                    for (snapshot in dataSnapshot.children) {
                        snapshot.child("idKlinik").getValue(String::class.java)?.let {
                            keyKlinikList.add(
                                it
                            )
                        }
                    }
                    pencarianKlinikView.dokterTersedia(keyKlinikList.distinct())
                } else {
                    pencarianKlinikView.dokterKlinikKosong()
                }
            }
        })
    }

    fun cekKlinik(keyDokterList: List<String>) {
        klinikRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val pairKlinik = mutableListOf<Pair<String, Klinik>>()

                    for (snapshot in dataSnapshot.children) {
                        val key = snapshot.key
                        val klinik = snapshot.getValue(Klinik::class.java)

                        if (key != null && klinik != null) pairKlinik.add(Pair(key, klinik))
                    }

                    pairKlinik.forEach {
                        if (!keyDokterList.contains(it.first)) {
                            pairKlinik.remove(it)
                        }
                    }

                    pencarianKlinikView.tampilDaftarKlinik(pairKlinik)
                } else {
                    pencarianKlinikView.dokterKlinikKosong()
                }
            }
        })
    }
}