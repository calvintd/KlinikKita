package id.calvintd.klinikkita.presenter.klinik.pemeriksaan

import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.view.klinik.pemeriksaan.KelolaPendaftaranView

class KelolaPendaftaranPresenter(private val kelolaPendaftaranView: KelolaPendaftaranView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pendaftaranRef = reference.child("pendaftaran")
    private val pasienRef = reference.child("pasien")
    private val dokterRef = reference.child("dokter")
    private val klinikRef = reference.child("klinik")

    fun cekPendaftaran() {
        pendaftaranRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    kelolaPendaftaranView.pendaftaranKosong()
                } else {
                    val pairPendaftaran = mutableListOf<Pair<String, Pendaftaran>>()

                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.child("status").getValue(Int::class.java) == 0
                        ) {
                            val key = snapshot.key
                            val pendaftaran = snapshot.getValue(Pendaftaran::class.java)

                            if (key != null && pendaftaran != null) pairPendaftaran.add(
                                Pair(
                                    key,
                                    pendaftaran
                                )
                            )
                        }
                    }

                    if (pairPendaftaran.isEmpty()) {
                        kelolaPendaftaranView.pendaftaranKosong()
                    } else {
                        kelolaPendaftaranView.cekPendaftaran(pairPendaftaran)
                    }
                }
            }
        })
    }

    fun ambilDataPasien() {
        pasienRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairPasien = mutableListOf<Pair<String, String>>()

                for (snapshot in dataSnapshot.children) {
                    val key = snapshot.key
                    val namaPasien = snapshot.child("namaLengkap").getValue(String::class.java)

                    if (key != null && namaPasien != null) pairPasien.add(Pair(key, namaPasien))
                }

                kelolaPendaftaranView.olahDataPasien(pairPasien)
            }
        })
    }

    fun ambilDataDokter() {
        dokterRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairDokter = mutableListOf<Pair<String, Dokter>>()

                for (snapshot in dataSnapshot.children) {
                    val key = snapshot.key
                    val dokter = snapshot.getValue(Dokter::class.java)

                    if (key != null && dokter != null) pairDokter.add(Pair(key, dokter))
                }

                kelolaPendaftaranView.olahDataDokter(pairDokter)
            }
        })
    }

    fun ambilDataKlinik() {
        klinikRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairKlinik = mutableListOf<Pair<String, String>>()

                for (snapshot in dataSnapshot.children) {
                    val key = snapshot.key
                    val klinik = snapshot.child("namaKlinik").getValue(String::class.java)

                    if (key != null && klinik != null) pairKlinik.add(Pair(key, klinik))
                }

                kelolaPendaftaranView.olahDataKlinik(pairKlinik)
            }
        })
    }

    fun tidakHadirPendaftaran(idPendaftaran: String) {
        pendaftaranRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key.equals(idPendaftaran)) {
                        pendaftaranRef.child(idPendaftaran).child("status").setValue(-1)
                        break
                    }
                }

                kelolaPendaftaranView.tidakHadirPendaftaran()
            }
        })
    }
}