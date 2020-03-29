package id.calvintd.klinikkita.activity.pasien.pemeriksaan

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.pasien.pemeriksaan.RiwayatPemeriksaanPasienAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.itemmodel.database.Pemeriksaan
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran
import id.calvintd.klinikkita.itemmodel.internal.PemeriksaanInternal
import id.calvintd.klinikkita.presenter.pasien.pemeriksaan.RiwayatPemeriksaanPasienPresenter
import id.calvintd.klinikkita.view.RiwayatPemeriksaanView

class RiwayatPemeriksaanPasienActivity : AppCompatActivity(), RiwayatPemeriksaanView {
    private lateinit var txtPemeriksaanKosong: TextView
    private lateinit var rvPemeriksaanPasien: RecyclerView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String
    private lateinit var idPasien: String

    private val presenter = RiwayatPemeriksaanPasienPresenter(this)

    private val listPemeriksaanPasien = mutableListOf<PemeriksaanInternal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_pemeriksaan_pasien)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtPemeriksaanKosong = findViewById(R.id.txtRiwayatPemeriksaanPasienKosong)
        rvPemeriksaanPasien = findViewById(R.id.rvRiwayatPemeriksaanPasien)

        txtPemeriksaanKosong.visibility = View.GONE

        idPasien = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_patient_key),
            defaultKey
        ).toString()

        presenter.cekPemeriksaan()
    }

    override fun cekPemeriksaan(pairPemeriksaan: List<Pair<String, Pemeriksaan>>) {
        for (i in pairPemeriksaan.indices) {
            listPemeriksaanPasien.add(
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
        TODO("Not yet implemented")
    }

    override fun olahDataPendaftaran(pairPendaftaran: List<Pair<String, Pendaftaran>>) {
        for (i in listPemeriksaanPasien.indices) {
            for (j in pairPendaftaran.indices) {
                if (listPemeriksaanPasien[i].idPendaftaran.equals(pairPendaftaran[j].first)) {
                    listPemeriksaanPasien[i].idPasien = pairPendaftaran[j].second.idPasien
                    listPemeriksaanPasien[i].idDokter = pairPendaftaran[j].second.idDokter
                    listPemeriksaanPasien[i].keluhan = pairPendaftaran[j].second.keluhan
                    break
                }
            }
        }

        presenter.ambilDataPasien()
    }

    override fun olahDataPasien(pairPasien: List<Pair<String, String>>) {
        for (i in listPemeriksaanPasien.indices) {
            for (j in pairPasien.indices) {
                if (listPemeriksaanPasien[i].idPasien.equals(pairPasien[j].first)) {
                    listPemeriksaanPasien[i].namaPasien = pairPasien[j].second
                    break
                }
            }
        }

        presenter.ambilDataDokter()
    }

    override fun olahDataDokter(pairDokter: List<Pair<String, Dokter>>) {
        for (i in listPemeriksaanPasien.indices) {
            for (j in pairDokter.indices) {
                if (listPemeriksaanPasien[i].idDokter.equals(pairDokter[j].first)) {
                    listPemeriksaanPasien[i].namaDokter = pairDokter[j].second.namaDokter
                    listPemeriksaanPasien[i].idKlinik = pairDokter[j].second.idKlinik
                }
            }
        }

        presenter.ambilDataKlinik()
    }

    override fun olahDataKlinik(pairKlinik: List<Pair<String, String>>) {
        for (i in listPemeriksaanPasien.indices) {
            for (j in pairKlinik.indices) {
                if (listPemeriksaanPasien[i].idKlinik.equals(pairKlinik[j].first)) {
                    listPemeriksaanPasien[i].namaKlinik = pairKlinik[j].second
                }
            }
        }

        for (i in listPemeriksaanPasien.indices) {
            if (!listPemeriksaanPasien[i].idPasien.equals(idPasien)) {
                listPemeriksaanPasien.removeAt(i)
            }
        }

        txtPemeriksaanKosong.visibility = View.GONE
        rvPemeriksaanPasien.visibility = View.VISIBLE

        rvPemeriksaanPasien.layoutManager = LinearLayoutManager(this)
        rvPemeriksaanPasien.adapter = RiwayatPemeriksaanPasienAdapter(listPemeriksaanPasien)
    }
}
