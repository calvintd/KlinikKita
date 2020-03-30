package id.calvintd.klinikkita.presenter.klinik

import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.presenter.PendaftaranPresenter
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarKlinikPresenter(
    private val pendaftaranView: PendaftaranView,
    private val dataKlinik: Klinik
) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val klinikChild = "klinik"
    private val nomorHP = dataKlinik.nomorHP
    private val kataSandi = dataKlinik.password
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
            if (userId != null) {
                var duplikat = false

                reference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        pendaftaranView.error()
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (snapshot in dataSnapshot.child(klinikChild).children) {
                            if (snapshot.child("nomorHP").toString() == nomorHP) {
                                duplikat = true
                            }
                        }

                        if (duplikat) {
                            pendaftaranView.nomorHPTerdaftar(txtNomorHP)
                        } else {
                            reference.child(klinikChild).child(userId).setValue(dataKlinik)
                            pendaftaranView.pendaftaranSukses()
                        }
                    }
                })
            }
        }
    }
}