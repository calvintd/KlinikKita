package id.calvintd.klinikkita.presenter.klinik

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.view.PengaturanView

class PengaturanKlinikPresenter(private val pengaturanView: PengaturanView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val klinikRef = reference.child("klinik")

    fun verifikasi(
        idKlinik: String,
        kataSandiLama: String,
        kataSandiBaru: String,
        kataSandiUlangBaru: String
    ) {
        var terisi = true
        var terulang = true

        if (kataSandiLama.isEmpty() || kataSandiBaru.isEmpty() || kataSandiUlangBaru.isEmpty()) {
            terisi = false
        } else if (!kataSandiBaru.equals(kataSandiUlangBaru)) {
            terulang = false
        }

        pengaturanView.cekKolomPasswordLama()
        pengaturanView.cekKolomPasswordBaru()
        pengaturanView.cekKolomUlangPasswordBaru()
        pengaturanView.cekUlangPasswordBaru(terulang)

        if (terisi && terulang) {
            klinikRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    pengaturanView.error()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.key.equals(idKlinik)) {
                            if (snapshot.child("password").getValue(String::class.java)
                                    .equals(kataSandiLama)
                            ) {
                                pengaturanView.cekPasswordLama(true)
                                pengaturanView.konfirmasi(kataSandiBaru)
                            } else {
                                pengaturanView.cekPasswordLama(false)
                            }
                            break
                        }
                    }
                }
            })
        }
    }

    fun ubahPassword(idKlinik: String, kataSandiBaru: String) {
        klinikRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                pengaturanView.error()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key.equals(idKlinik)) {
                        klinikRef.child(idKlinik).child("password").setValue(kataSandiBaru)
                        pengaturanView.ubahKataSandi(kataSandiBaru)
                    }
                }
            }
        })
    }
}