package id.calvintd.klinikkita.activity.klinik.dokter

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.klinik.dokter.KelolaDokterAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.presenter.klinik.dokter.KelolaDokterPresenter
import id.calvintd.klinikkita.view.klinik.dokter.KelolaDokterView

class KelolaDokterActivity : AppCompatActivity(), KelolaDokterView {
    private lateinit var imgTambahDokter: ImageView
    private lateinit var txtDaftarDokterKosong: TextView
    private lateinit var rvDaftarDokter: RecyclerView

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)

    private var idKlinik: String? = null

    val linearLayoutManager = LinearLayoutManager(this)

    private val presenter = KelolaDokterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_dokter)

        imgTambahDokter = findViewById(R.id.imgTambahDokter)
        txtDaftarDokterKosong = findViewById(R.id.txtDaftarDokterKosong)
        rvDaftarDokter = findViewById(R.id.rvDaftarDokter)

        rvDaftarDokter.layoutManager = linearLayoutManager

        imgTambahDokter.setOnClickListener {
            startActivity(Intent(this, TambahDokterActivity::class.java))
        }

        idKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_key),
            defaultKey
        )

        idKlinik?.let { presenter.cekDokterKlinik(it) }
    }

    override fun daftarDokterKosong() {
        txtDaftarDokterKosong.visibility = View.VISIBLE
        rvDaftarDokter.visibility = View.GONE
    }

    override fun isiDaftarDokter(listKeyDokter: List<String>, listDokter: List<Dokter>) {
        txtDaftarDokterKosong.visibility = View.GONE
        rvDaftarDokter.visibility = View.VISIBLE
        rvDaftarDokter.adapter =
            KelolaDokterAdapter(listKeyDokter, listDokter, { key: String, dokter: Dokter ->
                startActivity(
                    Intent(this, UbahDataDokterActivity::class.java)
                        .putExtra(resources.getString(R.string.extras_key), key)
                        .putExtra(resources.getString(R.string.parcelable_doctor), dokter)
                )
            }, { key: String ->
                val builder = AlertDialog.Builder(this)
                    .setTitle(resources.getString(R.string.clinic_manage_doctors_delete_doctor_header))
                    .setMessage(resources.getString(R.string.clinic_manage_doctors_delete_doctor_message))
                    .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                        presenter.hapusDokterKlinik(key)
                    }
                    .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                    }.show()
            })
    }

    override fun dokterTerhapus() {
        idKlinik?.let { presenter.cekDokterKlinik(it) }
        Toast.makeText(
            this,
            R.string.clinic_manage_doctors_delete_doctor_deleted_toast,
            Toast.LENGTH_LONG
        ).show()
    }
}
