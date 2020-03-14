package id.calvintd.klinikkita.presenter.pendaftaran

import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarPasienPresenter(
    private val pendaftaranView: PendaftaranView,
    private val dataPasien: Pasien
) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val nomorHP = dataPasien.nomorHP
    private val kataSandi = dataPasien.password
    private val pendaftaranPresenter =
        PendaftaranPresenter(
            pendaftaranView
        )

    fun cekFormulir(
        daftarKolom: List<Kolom>,
        txtNomorHP: TextView,
        kataSandiUlang: String,
        txtKataSandiUlang: TextView,
        persetujuan: Boolean,
        txtPersetujuan: TextView
    ) {
        val terverifikasi = pendaftaranPresenter.verifikasiData(
            daftarKolom,
            nomorHP,
            txtNomorHP,
            kataSandi,
            kataSandiUlang,
            txtKataSandiUlang,
            persetujuan,
            txtPersetujuan
        )

        if (terverifikasi) {
            val userId = reference.push().key
            val pasienRef = reference.child("pasien")
            if (userId != null) {
                var duplikat = false

                pasienRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot in dataSnapshot.children) {
                            if (snapshot.child("nomorHP").getValue(String::class.java) == nomorHP) {
                                duplikat = true
                                break
                            }
                        }

                        if (duplikat) {
                            pendaftaranView.nomorHPTerdaftar(txtNomorHP)
                        } else {
                            pasienRef.child(userId).setValue(dataPasien)
                            pendaftaranView.pendaftaranSukses()
                        }
                    }
                })
            }
        }
    }
}