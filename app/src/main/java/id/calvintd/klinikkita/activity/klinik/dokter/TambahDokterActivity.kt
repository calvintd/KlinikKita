package id.calvintd.klinikkita.activity.klinik.dokter

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.presenter.klinik.dokter.TambahDokterPresenter
import id.calvintd.klinikkita.util.DialogUtil
import id.calvintd.klinikkita.view.klinik.dokter.TambahDokterView

class TambahDokterActivity : AppCompatActivity(), TambahDokterView {
    private lateinit var edtNama: EditText
    private lateinit var txtKesalahanNama: TextView
    private lateinit var edtDeskripsi: EditText
    private lateinit var txtKesalahanDeskripsi: TextView
    private lateinit var btnTambah: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_dokter)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        edtNama = findViewById(R.id.edtNamaTambahDokter)
        txtKesalahanNama = findViewById(R.id.txtKesalahanNamaTambahDokter)
        edtDeskripsi = findViewById(R.id.edtDeskripsiTambahDokter)
        txtKesalahanDeskripsi = findViewById(R.id.txtKesalahanDeskripsiTambahDokter)
        btnTambah = findViewById(R.id.btnTambahDokter)

        val presenter = TambahDokterPresenter(this)

        val idKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_key),
            defaultKey
        )

        btnTambah.setOnClickListener {
            val nama = edtNama.text.toString()
            val deskripsi = edtDeskripsi.text.toString()
            val dokter = idKlinik?.let { Dokter(it, nama, deskripsi) }

            dokter?.let { it1 -> presenter.tambahDokter(it1) }
        }
    }

    override fun tambahDokter() {
        Toast.makeText(this, R.string.clinic_manage_doctors_add_doctor_added_toast, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun cekNama() {
        if (txtKesalahanNama.text.isEmpty()) {
            txtKesalahanNama.visibility = View.VISIBLE
        } else {
            txtKesalahanNama.visibility = View.GONE
        }
    }

    override fun cekDeskripsi() {
        if (txtKesalahanDeskripsi.text.isEmpty()) {
            txtKesalahanDeskripsi.visibility = View.VISIBLE
        } else {
            txtKesalahanDeskripsi.visibility = View.GONE
        }
    }

    override fun error() {
        DialogUtil.errorDialog(this, resources).show()
    }
}
