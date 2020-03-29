package id.calvintd.klinikkita.activity.pasien.daftar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.klinik.BerandaKlinikActivity
import id.calvintd.klinikkita.presenter.pasien.daftar.EntriKeluhanPresenter
import id.calvintd.klinikkita.view.pasien.daftar.EntriKeluhanView

class EntriKeluhanActivity : AppCompatActivity(), EntriKeluhanView {
    private lateinit var txtKlinik: TextView
    private lateinit var txtDokter: TextView
    private lateinit var edtKeluhan: EditText
    private lateinit var txtKesalahanKeluhan: TextView
    private lateinit var btnDaftar: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String

    private var bundle: Bundle? = null
    private lateinit var namaKlinik: String
    private lateinit var keyDokter: String
    private lateinit var namaDokter: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entri_keluhan)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtKlinik = findViewById(R.id.txtNamaKlinikEntriKeluhan)
        txtDokter = findViewById(R.id.txtNamaDokterEntriKeluhan)
        edtKeluhan = findViewById(R.id.edtKeluhanEntriKeluhan)
        txtKesalahanKeluhan = findViewById(R.id.txtKesalahanKeluhanEntriKeluhan)
        btnDaftar = findViewById(R.id.btnDaftarPemeriksaan)

        bundle = intent.extras

        namaKlinik = bundle?.getString(resources.getString(R.string.extras_name_clinic)).toString()
        keyDokter = bundle?.getString(resources.getString(R.string.extras_key_doctor)).toString()
        namaDokter = bundle?.getString(resources.getString(R.string.extras_name_doctor)).toString()

        txtKlinik.text = namaKlinik
        txtDokter.text = namaDokter

        val presenter = EntriKeluhanPresenter(this)

        val idPasien = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_patient_key),
            defaultKey)

        btnDaftar.setOnClickListener {
            if (idPasien != null) {
                presenter.entriKeluhan(idPasien, keyDokter, edtKeluhan.text.toString())
            }
        }
    }

    override fun entriKeluhan() {
        txtKesalahanKeluhan.visibility = View.GONE
        Toast.makeText(this, R.string.patient_appointments_registration_details_registered_toast, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, BerandaKlinikActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    override fun keluhanKosong() {
        txtKesalahanKeluhan.visibility = View.VISIBLE
    }
}
