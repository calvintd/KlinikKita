package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.klinik.BerandaKlinikActivity
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.itemmodel.internal.PendaftaranKlinikInternal
import id.calvintd.klinikkita.presenter.klinik.pemeriksaan.DiagnosisPemeriksaanPresenter
import id.calvintd.klinikkita.view.klinik.pemeriksaan.DiagnosisPemeriksaanView

class DiagnosisPemeriksaanActivity : AppCompatActivity(), DiagnosisPemeriksaanView {
    private lateinit var txtNamaPasien: TextView
    private lateinit var txtNamaKlinik: TextView
    private lateinit var txtNamaDokter: TextView
    private lateinit var txtIsiKeluhan: TextView
    private lateinit var edtDiagnosis: EditText
    private lateinit var txtDiagnosisKosong: TextView
    private lateinit var edtPengobatan: EditText
    private lateinit var txtPengobatanKosong: TextView
    private lateinit var btnKonfirmasi: Button

    private var bundle: Bundle? = null
    private var pendaftaran: PendaftaranKlinikInternal? = null
    private lateinit var idPendaftaran: String

    private val presenter = DiagnosisPemeriksaanPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis_pemeriksaan)

        txtNamaPasien = findViewById(R.id.txtNamaPasienDiagnosisPemeriksaan)
        txtNamaKlinik = findViewById(R.id.txtNamaKlinikDiagnosisPemeriksaan)
        txtNamaDokter = findViewById(R.id.txtNamaDokterDiagnosisPemeriksaan)
        txtIsiKeluhan = findViewById(R.id.txtIsiKeluhanDiagnosisPemeriksaan)
        edtDiagnosis = findViewById(R.id.edtDiagnosisDiagnosisPemeriksaan)
        txtDiagnosisKosong = findViewById(R.id.txtDiagnosisDiagnosisPemeriksaanKosong)
        edtPengobatan = findViewById(R.id.edtPengobatanDiagnosisPemeriksaan)
        txtPengobatanKosong = findViewById(R.id.txtPengobatanDiagnosisPemeriksaanKosong)
        btnKonfirmasi = findViewById(R.id.btnKonfirmasiDiagnosisPemeriksaan)

        bundle = intent.extras

        pendaftaran =
            bundle?.getParcelable(resources.getString(R.string.parcelable_internal_clinic_registration))

        pendaftaran?.let {
            txtNamaPasien.text = it.namaPasien
            txtNamaKlinik.text = it.namaKlinik
            txtNamaDokter.text = it.namaDokter
            txtIsiKeluhan.text = it.keluhan
        }

        idPendaftaran = pendaftaran?.idPendaftaran.toString()

        btnKonfirmasi.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.clinic_appointments_list_diagnosis_confirmation_header))
                .setMessage(resources.getString(R.string.clinic_appointments_list_diagnosis_confirmation_message))
                .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                    pendaftaran?.let {
                        presenter.diagnosis(Pemeriksaan(
                            idPendaftaran,
                            System.currentTimeMillis(),
                            edtDiagnosis.text.toString(),
                            edtPengobatan.text.toString()
                        ))
                    }
                }
                .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                }
                .show()
        }
    }

    override fun cekDiagnosis() {
        if (edtDiagnosis.text.isEmpty()) {
            txtDiagnosisKosong.visibility = View.VISIBLE
        } else {
            txtDiagnosisKosong.visibility = View.GONE
        }
    }

    override fun cekPengobatan() {
        if (edtPengobatan.text.isEmpty()) {
            txtPengobatanKosong.visibility = View.VISIBLE
        } else {
            txtPengobatanKosong.visibility = View.GONE
        }
    }

    override fun diagnosisSukses() {
        Toast.makeText(this, R.string.clinic_appointments_list_diagnosis_confirmed_toast, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, BerandaKlinikActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
