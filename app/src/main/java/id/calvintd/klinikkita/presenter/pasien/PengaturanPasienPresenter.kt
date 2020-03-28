package id.calvintd.klinikkita.presenter.pasien

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.view.PengaturanView

class PengaturanPasienPresenter(private val pengaturanView: PengaturanView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val pasienRef = reference.child("pasien")

    fun ubahPassword (idPasien: String, kataSandiLama: String, kataSandiBaru: String, kataSandiUlangBaru: String) {
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
            pasienRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.key.equals(idPasien)) {
                            if (snapshot.child("password").getValue(String::class.java).equals(kataSandiLama)) {
                                pasienRef.child(idPasien).child("password").setValue(kataSandiBaru)
                                pengaturanView.cekPasswordLama(true)
                                pengaturanView.ubahKataSandi(kataSandiBaru)
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
}