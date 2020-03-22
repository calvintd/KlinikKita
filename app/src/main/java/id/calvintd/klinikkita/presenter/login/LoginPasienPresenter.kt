package id.calvintd.klinikkita.presenter.login

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.view.login.LoginPasienView

class LoginPasienPresenter(private val loginPasienView: LoginPasienView) {
    private val reference = FirebaseDatabase.getInstance().reference

    fun login(nomorHP: String, kataSandi: String) {
        if (nomorHP.isEmpty() || kataSandi.isEmpty()) {
            loginPasienView.kolomKosong()
        } else {
            val pasienRef = reference.child("pasien")
            pasienRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
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
                                val pasien = snapshot.getValue(Pasien::class.java)

                                if (key != null && pasien != null) {
                                    loginPasienView.loginSukses(key, pasien)
                                }
                            } else {
                                loginPasienView.kolomSalah()
                            }
                        } else {
                            loginPasienView.kolomSalah()
                        }
                    }
                }
            })
        }
    }
}