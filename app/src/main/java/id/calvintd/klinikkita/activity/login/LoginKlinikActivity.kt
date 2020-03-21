package id.calvintd.klinikkita.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.pendaftaran.PendaftaranKlinikActivity
import id.calvintd.klinikkita.presenter.login.LoginKlinikPresenter
import id.calvintd.klinikkita.view.LoginView

class LoginKlinikActivity : AppCompatActivity(), LoginView {
    private lateinit var edtNomorPonsel: TextView
    private lateinit var edtKataSandi: TextView
    private lateinit var txtKesalahan: TextView
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_klinik)

        edtNomorPonsel = findViewById(R.id.nomorPonselKlinik)
        edtKataSandi = findViewById(R.id.kataSandiKlinik)
        txtKesalahan = findViewById(R.id.txtKesalahanLoginKlinik)
        btnMasuk = findViewById(R.id.btnLoginKlinik)
        txtDaftar = findViewById(R.id.txtDaftarKlinik)

        val nomorHP = edtNomorPonsel.text.toString()
        val kataSandi = edtKataSandi.text.toString()

        val presenter = LoginKlinikPresenter(this)

        presenter.login(nomorHP, kataSandi)

        btnMasuk.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }

        txtDaftar.setOnClickListener {
            startActivity(Intent(this, PendaftaranKlinikActivity::class.java))
        }
    }

    override fun kolomKosong() {
        TODO("Not yet implemented")
    }

    override fun kolomSalah() {
        TODO("Not yet implemented")
    }

    override fun loginSukses() {
        TODO("Not yet implemented")
    }
}
