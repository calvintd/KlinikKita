package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.klinik.pemeriksaan.RiwayatPemeriksaanKlinikAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.itemmodel.internal.PemeriksaanInternal
import id.calvintd.klinikkita.presenter.klinik.pemeriksaan.RiwayatPemeriksaanKlinikPresenter
import id.calvintd.klinikkita.view.RiwayatPemeriksaanView

class RiwayatPemeriksaanKlinikActivity : AppCompatActivity(),
    RiwayatPemeriksaanView {
    private lateinit var txtPemeriksaanKosong: TextView
    private lateinit var rvPemeriksaanKlinik: RecyclerView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String
    private lateinit var idKlinik: String

    private val presenter = RiwayatPemeriksaanKlinikPresenter(this)

    private val listPemeriksaanKlinik = mutableListOf<PemeriksaanInternal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_pemeriksaan_klinik)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtPemeriksaanKosong = findViewById(R.id.txtRiwayatPemeriksaanKlinikKosong)
        rvPemeriksaanKlinik = findViewById(R.id.rvRiwayatPemeriksaanKlinik)

        idKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_key),
            defaultKey
        ).toString()

        presenter.cekPemeriksaan()
    }

    override fun cekPemeriksaan(pairPemeriksaan: List<Pair<String, Pemeriksaan>>) {
        for (i in pairPemeriksaan.indices) {
            listPemeriksaanKlinik.add(
                PemeriksaanInternal(
                    idPemeriksaan = pairPemeriksaan[i].first,
                    waktu = pairPemeriksaan[i].second.waktu,
                    diagnosis = pairPemeriksaan[i].second.diagnosis,
                    pengobatan = pairPemeriksaan[i].second.pengobatan,
                    idPendaftaran = pairPemeriksaan[i].second.idPendaftaran,
                    keluhan = null,
                    idPasien = null,
                    namaPasien = null,
                    idDokter = null,
                    namaDokter = null,
                    idKlinik = null,
                    namaKlinik = null
                )
            )
        }

        presenter.ambilDataPendaftaran()
    }

    override fun pemeriksaanKosong() {
        txtPemeriksaanKosong.visibility = View.VISIBLE
        rvPemeriksaanKlinik.visibility = View.GONE
    }

    override fun olahDataPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>) {
        for (i in listPemeriksaanKlinik.indices) {
            for (j in pairPendaftaran.indices) {
                if (listPemeriksaanKlinik[i].idPendaftaran.equals(pairPendaftaran[j].first)) {
                    listPemeriksaanKlinik[i].idPasien = pairPendaftaran[j].second.idPasien
                    listPemeriksaanKlinik[i].idDokter = pairPendaftaran[j].second.idDokter
                    listPemeriksaanKlinik[i].keluhan = pairPendaftaran[j].second.keluhan
                    break
                }
            }
        }

        presenter.ambilDataPasien()
    }

    override fun olahDataPasien(pairPasien: List<Pair<String, String>>) {
        for (i in listPemeriksaanKlinik.indices) {
            for (j in pairPasien.indices) {
                if (listPemeriksaanKlinik[i].idPasien.equals(pairPasien[j].first)) {
                    listPemeriksaanKlinik[i].namaPasien = pairPasien[j].second
                    break
                }
            }
        }

        presenter.ambilDataDokter()
    }

    override fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>) {
        for (i in listPemeriksaanKlinik.indices) {
            for (j in pairDokter.indices) {
                if (listPemeriksaanKlinik[i].idDokter.equals(pairDokter[j].first)) {
                    listPemeriksaanKlinik[i].namaDokter = pairDokter[j].second.namaDokter
                    listPemeriksaanKlinik[i].idKlinik = pairDokter[j].second.idKlinik
                }
            }
        }

        presenter.ambilDataKlinik()
    }

    override fun olahDataKlinik(pairKlinik: List<Pair<String, String>>) {
        for (i in listPemeriksaanKlinik.indices) {
            for (j in pairKlinik.indices) {
                if (listPemeriksaanKlinik[i].idKlinik.equals(pairKlinik[j].first)) {
                    listPemeriksaanKlinik[i].namaKlinik = pairKlinik[j].second
                }
            }
        }

        for (i in listPemeriksaanKlinik.indices) {
            if (!listPemeriksaanKlinik[i].idKlinik.equals(idKlinik)) {
                listPemeriksaanKlinik.removeAt(i)
            }
        }

        txtPemeriksaanKosong.visibility = View.GONE
        rvPemeriksaanKlinik.visibility = View.VISIBLE

        rvPemeriksaanKlinik.layoutManager = LinearLayoutManager(this)
        rvPemeriksaanKlinik.adapter = RiwayatPemeriksaanKlinikAdapter(listPemeriksaanKlinik)
    }
}
