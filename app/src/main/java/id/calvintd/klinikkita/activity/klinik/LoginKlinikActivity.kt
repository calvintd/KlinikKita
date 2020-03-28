package id.calvintd.klinikkita.activity.klinik

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.pendaftaran.PendaftaranKlinikActivity
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.presenter.login.LoginKlinikPresenter
import id.calvintd.klinikkita.view.klinik.LoginKlinikView

class LoginKlinikActivity : AppCompatActivity(),
    LoginKlinikView {
    private lateinit var edtNomorPonsel: TextView
    private lateinit var edtKataSandi: TextView
    private lateinit var txtKesalahan: TextView
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    private lateinit var sharedPrefEditor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_klinik)

        sharedPrefEditor =
            getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE).edit()

        edtNomorPonsel = findViewById(R.id.nomorPonselKlinik)
        edtKataSandi = findViewById(R.id.kataSandiKlinik)
        txtKesalahan = findViewById(R.id.txtKesalahanLoginKlinik)
        btnMasuk = findViewById(R.id.btnLoginKlinik)
        txtDaftar = findViewById(R.id.txtDaftarKlinik)

        val presenter = LoginKlinikPresenter(this)

        btnMasuk.setOnClickListener {
            val nomorHP = edtNomorPonsel.text.toString()
            val kataSandi = edtKataSandi.text.toString()
            presenter.login(nomorHP, kataSandi)
        }

        txtDaftar.setOnClickListener {
            startActivity(Intent(this, PendaftaranKlinikActivity::class.java))
        }
    }

    override fun kolomKosong() {
        txtKesalahan.visibility = View.VISIBLE
        txtKesalahan.text = resources.getString(R.string.key_login_empty)
    }

    override fun kolomSalah() {
        txtKesalahan.visibility = View.VISIBLE
        txtKesalahan.text = resources.getString(R.string.key_login_mistake)
    }

    override fun loginSukses(key: String, dataKlinik: Klinik) {
        txtKesalahan.visibility = View.GONE
        sharedPrefEditor.putString(resources.getString(R.string.shared_pref_clinic_key), key)
            .putString(resources.getString(R.string.shared_pref_clinic_name), dataKlinik.namaKlinik)
            .putString(resources.getString(R.string.shared_pref_patient_address), dataKlinik.alamat)
            .putString(resources.getString(R.string.shared_pref_clinic_city), dataKlinik.kota)
            .putString(
                resources.getString(R.string.shared_pref_clinic_phone_number),
                dataKlinik.nomorHP
            )
            .putString(
                resources.getString(R.string.shared_pref_clinic_password),
                dataKlinik.password
            )
            .apply()
        startActivity(
            Intent(this, BerandaKlinikActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
}
