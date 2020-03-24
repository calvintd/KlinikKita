package id.calvintd.klinikkita.activity.klinik.dokter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.presenter.klinik.dokter.UbahDataDokterPresenter
import id.calvintd.klinikkita.view.klinik.dokter.UbahDataDokterView

class UbahDataDokterActivity : AppCompatActivity(), UbahDataDokterView {
    private lateinit var edtNama: EditText
    private lateinit var txtKesalahanNama: TextView
    private lateinit var edtDeskripsi: EditText
    private lateinit var txtKesalahanDeskripsi: TextView
    private lateinit var btnUbahData: Button

    private val bundle: Bundle? = intent.extras
    private lateinit var key: String
    private var dokter: Dokter? = null

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_data_dokter)

        edtNama = findViewById(R.id.edtNamaUbahDataDokter)
        txtKesalahanNama = findViewById(R.id.txtKesalahanNamaUbahDataDokter)
        edtDeskripsi = findViewById(R.id.edtDeskripsiUbahDataDokter)
        txtKesalahanDeskripsi = findViewById(R.id.txtKesalahanDeskripsiUbahDataDokter)
        btnUbahData = findViewById(R.id.btnUbahDataDokter)

        val presenter = UbahDataDokterPresenter(this)

        bundle?.apply {
            key = getString(R.string.extras_key)
            dokter = getParcelable(resources.getString(R.string.parcelable_doctor))
        }

        dokter?.let { presenter.ubahDataDokter(key, it) }
    }

    override fun ubahDataDokter() {
        Toast.makeText(this, R.string.clinic_manage_doctors_edit_doctor_edited_toast, Toast.LENGTH_LONG).show()
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
}
