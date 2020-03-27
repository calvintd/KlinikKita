package id.calvintd.klinikkita.activity.pasien.pemeriksaan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.pasien.pemeriksaan.CekPendaftaranAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.itemmodel.internal.PendaftaranPasienInternal
import id.calvintd.klinikkita.presenter.pasien.pemeriksaan.CekPendaftaranPresenter
import id.calvintd.klinikkita.view.pasien.pemeriksaan.CekPendaftaranView

class CekPendaftaranActivity : AppCompatActivity(), CekPendaftaranView {
    private lateinit var txtDaftarKosong: TextView
    private lateinit var rvCekPendaftaran: RecyclerView

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)

    private val listPendaftaranPasien = mutableListOf<PendaftaranPasienInternal>()

    private val presenter = CekPendaftaranPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cek_pendaftaran_pasien)

        txtDaftarKosong = findViewById(R.id.txtCekPendaftaranPasienKosong)
        rvCekPendaftaran = findViewById(R.id.rvCekPendaftaranPasien)

        val idPasien = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_patient_key),
            defaultKey
        )

        idPasien?.let { presenter.cekPendaftaran(it) }
    }

    override fun pendaftaranKosong() {
        txtDaftarKosong.visibility = View.VISIBLE
        rvCekPendaftaran.visibility = View.GONE
    }

    override fun tampilPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>) {
        for (i in pairPendaftaran.indices) {
            listPendaftaranPasien.add(
                PendaftaranPasienInternal(
                    idPendaftaran = pairPendaftaran[i].first,
                    keluhan = pairPendaftaran[i].second.keluhan,
                    status = pairPendaftaran[i].second.status,
                    idPasien = pairPendaftaran[i].second.idPasien,
                    namaPasien = null,
                    idDokter = pairPendaftaran[i].second.idDokter,
                    namaDokter = null,
                    idKlinik = null,
                    namaKlinik = null
                )
            )
        }
    }

    override fun olahDataPasien(pairPasien: List<Pair<String, String>>) {
        for (i in listPendaftaranPasien.indices) {
            for (j in pairPasien.indices) {
                if (listPendaftaranPasien[i].idPasien.equals(pairPasien[j].first)) {
                    listPendaftaranPasien[i].namaPasien = pairPasien[j].second
                    break
                }
            }
        }

        presenter.ambilDataDokter()
    }

    override fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>) {
        for (i in listPendaftaranPasien.indices) {
            for (j in pairDokter.indices) {
                if (listPendaftaranPasien[i].idDokter.equals(pairDokter[j].first)) {
                    listPendaftaranPasien[i].namaDokter = pairDokter[j].second.nama
                    listPendaftaranPasien[i].idKlinik = pairDokter[j].second.idKlinik
                    break
                }
            }
        }

        presenter.ambilDataKlinik()
    }

    override fun olahDataKlinik(pairKlinik: List<Pair<String, String>>) {
        for (i in listPendaftaranPasien.indices) {
            for (j in pairKlinik.indices) {
                if (listPendaftaranPasien[i].idKlinik.equals(pairKlinik[j].first)) {
                    listPendaftaranPasien[i].namaKlinik = pairKlinik[j].second
                    break
                }
            }
        }

        txtDaftarKosong.visibility = View.GONE
        rvCekPendaftaran.visibility = View.VISIBLE
        rvCekPendaftaran.layoutManager = LinearLayoutManager(this)
        rvCekPendaftaran.adapter = CekPendaftaranAdapter(listPendaftaranPasien)
    }
}
