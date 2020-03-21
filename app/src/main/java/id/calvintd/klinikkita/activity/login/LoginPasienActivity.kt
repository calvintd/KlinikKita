package id.calvintd.klinikkita.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.pendaftaran.PendaftaranPasienActivity
import id.calvintd.klinikkita.presenter.login.LoginPasienPresenter
import id.calvintd.klinikkita.view.LoginView

class LoginPasienActivity : AppCompatActivity(), LoginView {
    private lateinit var edtNomorPonsel: TextView
    private lateinit var edtKataSandi: TextView
    private lateinit var txtKesalahan: TextView
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pasien)

        edtNomorPonsel = findViewById(R.id.nomorPonselPasien)
        edtKataSandi = findViewById(R.id.kataSandiPasien)
        txtKesalahan = findViewById(R.id.txtKesalahanLoginPasien)
        btnMasuk = findViewById(R.id.btnLoginPasien)
        txtDaftar = findViewById(R.id.txtDaftarPasien)

        val nomorHP = edtNomorPonsel.text.toString()
        val kataSandi = edtKataSandi.text.toString()

        val presenter = LoginPasienPresenter(this)

        presenter.login(nomorHP, kataSandi)

        btnMasuk.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }

        txtDaftar.setOnClickListener {
            startActivity(Intent(this, PendaftaranPasienActivity::class.java))
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
