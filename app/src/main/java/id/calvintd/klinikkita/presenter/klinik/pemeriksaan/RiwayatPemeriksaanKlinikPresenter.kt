package id.calvintd.klinikkita.presenter.klinik.pemeriksaan

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.view.klinik.pemeriksaan.RiwayatPemeriksaanKlinikView

class RiwayatPemeriksaanKlinikPresenter(private val riwayatPemeriksaanKlinikView: RiwayatPemeriksaanKlinikView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pemeriksaanRef = reference.child("pemeriksaan")
    private val pendaftaranRef = reference.child("pendaftaran")
    private val pasienRef = reference.child("pasien")
    private val dokterRef = reference.child("dokter")
    private val klinikRef = reference.child("klinik")

    fun cekPemeriksaan() {
        pemeriksaanRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    riwayatPemeriksaanKlinikView.pemeriksaanKosong()
                } else {
                    val pairPemeriksaan = mutableListOf<Pair<String, Pemeriksaan>>()

                    for (snapshot in dataSnapshot.children) {
                        val key = snapshot.key
                        val pemeriksaan = snapshot.getValue(Pemeriksaan::class.java)

                        if (key != null && pemeriksaan != null) pairPemeriksaan.add(
                            Pair(
                                key,
                                pemeriksaan
                            )
                        )
                    }

                    if (pairPemeriksaan.isEmpty()) {
                        riwayatPemeriksaanKlinikView.pemeriksaanKosong()
                    } else {
                        riwayatPemeriksaanKlinikView.cekPemeriksaan(pairPemeriksaan)
                    }
                }
            }
        })
    }

    fun ambilDataPendaftaran() {
        pendaftaranRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairPendaftaran = mutableListOf<Pair<String, String>>()

                for (snapshot in dataSnapshot.children) {
                    val key = snapshot.key
                    val keluhan = snapshot.child("keluhan").getValue(String::class.java)

                    if (key != null && keluhan != null) pairPendaftaran.add(Pair(key, keluhan))
                }

                riwayatPemeriksaanKlinikView.olahDataPendaftaran(pairPendaftaran)
            }
        })
    }

    fun ambilDataPasien() {
        pasienRef.addListenerForSingleValueEvent(object : ValueEventListener{
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

                riwayatPemeriksaanKlinikView.olahDataPasien(pairPasien)
            }
        })
    }

    fun ambilDataDokter() {
        dokterRef.addListenerForSingleValueEvent(object : ValueEventListener{
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

                riwayatPemeriksaanKlinikView.olahDataDokter(pairDokter)
            }
        })
    }

    fun ambilDataKlinik() {
        klinikRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val pairKlinik = mutableListOf<Pair<String, String>>()

                for (snapshot in dataSnapshot.children) {
                    val key = snapshot.key
                    val namaKlinik = snapshot.child("namaKlinik").getValue(String::class.java)

                    if (key != null && namaKlinik != null) pairKlinik.add(Pair(key, namaKlinik))
                }

                riwayatPemeriksaanKlinikView.olahDataKlinik(pairKlinik)
            }
        })
    }
}