package id.calvintd.klinikkita.activity.pasien

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.pendaftaran.PendaftaranPasienActivity
import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.presenter.login.LoginPasienPresenter
import id.calvintd.klinikkita.view.pasien.LoginPasienView

class LoginPasienActivity : AppCompatActivity(),
    LoginPasienView {
    private lateinit var edtNomorPonsel: TextView
    private lateinit var edtKataSandi: TextView
    private lateinit var txtKesalahan: TextView
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    private lateinit var sharedPrefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pasien)

        sharedPrefEditor =
            getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE).edit()

        edtNomorPonsel = findViewById(R.id.nomorPonselPasien)
        edtKataSandi = findViewById(R.id.kataSandiPasien)
        txtKesalahan = findViewById(R.id.txtKesalahanLoginPasien)
        btnMasuk = findViewById(R.id.btnLoginPasien)
        txtDaftar = findViewById(R.id.txtDaftarPasien)

        val nomorHP = edtNomorPonsel.text.toString()
        val kataSandi = edtKataSandi.text.toString()

        val presenter = LoginPasienPresenter(this)

        btnMasuk.setOnClickListener {
            presenter.login(nomorHP, kataSandi)
        }

        txtDaftar.setOnClickListener {
            startActivity(Intent(this, PendaftaranPasienActivity::class.java))
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

    override fun loginSukses(key: String, dataPasien: Pasien) {
        txtKesalahan.visibility = View.GONE
        sharedPrefEditor.putString(resources.getString(R.string.shared_pref_patient_key), key)
            .putString(
                resources.getString(R.string.shared_pref_patient_full_name),
                dataPasien.namaLengkap
            )
            .putString(
                resources.getString(R.string.shared_pref_patient_nickname),
                dataPasien.namaPanggilan
            )
            .putString(resources.getString(R.string.shared_pref_patient_address), dataPasien.alamat)
            .putString(resources.getString(R.string.shared_pref_patient_city), dataPasien.kota)
            .putString(
                resources.getString(R.string.shared_pref_patient_phone_number),
                dataPasien.nomorHP
            )
            .putString(
                resources.getString(R.string.shared_pref_patient_password),
                dataPasien.password
            ).apply()
        startActivity(
            Intent(this, BerandaPasienActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }
}
