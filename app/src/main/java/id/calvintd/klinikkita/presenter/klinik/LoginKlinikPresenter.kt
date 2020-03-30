package id.calvintd.klinikkita.presenter.klinik

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.view.klinik.LoginKlinikView

class LoginKlinikPresenter(private val loginKlinikView: LoginKlinikView) {
    private val reference = FirebaseDatabase.getInstance().reference
    private val klinikRef = reference.child("klinik")

    fun login(nomorHP: String, kataSandi: String) {
        if (nomorHP.isEmpty() || kataSandi.isEmpty()) {
            loginKlinikView.kolomKosong()
        } else {
            klinikRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    loginKlinikView.error()
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.child("nomorHP").getValue(String::class.java)
                                .equals(nomorHP)
                        ) {
                            if (snapshot.child("password").getValue(String::class.java)
                                    .equals(kataSandi)
                            ) {
                                val key = snapshot.key
                                val klinik = snapshot.getValue(Klinik::class.java)

                                if (key != null && klinik != null) {
                                    loginKlinikView.loginSukses(key, klinik)
                                }
                            } else {
                                loginKlinikView.kolomSalah()
                            }
                            break
                        } else {
                            loginKlinikView.kolomSalah()
                        }
                    }
                }
            })
        }
    }
}