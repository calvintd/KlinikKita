package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.klinik.pemeriksaan.KelolaPendaftaranAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.itemmodel.internal.PendaftaranKlinikInternal
import id.calvintd.klinikkita.presenter.klinik.pemeriksaan.KelolaPendaftaranPresenter
import id.calvintd.klinikkita.view.klinik.pemeriksaan.KelolaPendaftaranView

class KelolaPendaftaranActivity : AppCompatActivity(), KelolaPendaftaranView {
    private lateinit var txtKosong: TextView
    private lateinit var rvPendaftaran: RecyclerView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String

    private var idKlinik: String? = null

    private val presenter = KelolaPendaftaranPresenter(this)

    private val listPendaftaranKlinik = mutableListOf<PendaftaranKlinikInternal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_pendaftaran)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtKosong = findViewById(R.id.txtKelolaPendaftaranKlinikKosong)
        rvPendaftaran = findViewById(R.id.rvKelolaPendaftaranKlinik)

        idKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_key),
            defaultKey
        )

        idKlinik?.let { presenter.cekPendaftaran() }
    }

    override fun cekPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>) {
        for (i in pairPendaftaran.indices) {
            listPendaftaranKlinik.add(
                PendaftaranKlinikInternal(
                    idPendaftaran = pairPendaftaran[i].first,
                    keluhan = pairPendaftaran[i].second.keluhan,
                    idPasien = pairPendaftaran[i].second.idPasien,
                    namaPasien = null,
                    idDokter = pairPendaftaran[i].second.idDokter,
                    namaDokter = null,
                    idKlinik = null,
                    namaKlinik = null
                )
            )
        }

        presenter.ambilDataPasien()
    }

    override fun pendaftaranKosong() {
        txtKosong.visibility = View.VISIBLE
        rvPendaftaran.visibility = View.GONE

        rvPendaftaran.layoutManager = LinearLayoutManager(this)
        rvPendaftaran.adapter = KelolaPendaftaranAdapter(listOf(), { _ ->

        }, { _ ->

        })
    }

    override fun olahDataPasien(pairPasien: List<Pair<String, String>>) {
        for (i in listPendaftaranKlinik.indices) {
            for (j in pairPasien.indices) {
                if (listPendaftaranKlinik[i].idPasien.equals(pairPasien[j].first)) {
                    listPendaftaranKlinik[i].namaPasien = pairPasien[j].second
                    break
                }
            }
        }

        presenter.ambilDataDokter()
    }

    override fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>) {
        for (i in listPendaftaranKlinik.indices) {
            for (j in pairDokter.indices) {
                if (listPendaftaranKlinik[i].idDokter.equals(pairDokter[j].first)) {
                    listPendaftaranKlinik[i].namaDokter = pairDokter[j].second.namaDokter
                    listPendaftaranKlinik[i].idKlinik = pairDokter[j].second.idKlinik
                    break
                }
            }
        }

        presenter.ambilDataKlinik()
    }

    override fun olahDataKlinik(pairKlinik: List<Pair<String, String>>) {
        for (i in listPendaftaranKlinik.indices) {
            for (j in pairKlinik.indices) {
                if (listPendaftaranKlinik[i].idKlinik.equals(pairKlinik[j].first)) {
                    listPendaftaranKlinik[i].namaKlinik = pairKlinik[j].second
                    break
                }
            }
        }

        for (i in listPendaftaranKlinik.indices) {
            if (!listPendaftaranKlinik[i].idKlinik.equals(idKlinik)) {
                listPendaftaranKlinik.removeAt(i)
            }
        }

        txtKosong.visibility = View.GONE
        rvPendaftaran.visibility = View.VISIBLE
        rvPendaftaran.layoutManager = LinearLayoutManager(this)
        rvPendaftaran.adapter = KelolaPendaftaranAdapter(
            listPendaftaranKlinik,
            { pendaftaran: PendaftaranKlinikInternal ->
                startActivity(
                    Intent(this, DiagnosisPemeriksaanActivity::class.java)
                        .putExtra(
                            resources.getString(R.string.parcelable_internal_clinic_registration),
                            pendaftaran
                        )
                )
            },
            { key: String ->
                AlertDialog.Builder(this)
                    .setTitle(resources.getString(R.string.clinic_appointments_list_pending_no_show_title))
                    .setMessage(resources.getString(R.string.clinic_appointments_list_pending_no_show_message))
                    .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                        presenter.tidakHadirPendaftaran(key)
                    }
                    .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                    }.show()
            })
    }

    override fun tidakHadirPendaftaran() {
        presenter.cekPendaftaran()
        Toast.makeText(
            this,
            R.string.clinic_appointments_list_pending_no_show_marked_toast,
            Toast.LENGTH_LONG
        ).show()
    }
}
