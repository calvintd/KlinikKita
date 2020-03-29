package id.calvintd.klinikkita.activity.pasien.daftar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.pasien.daftar.PencarianKlinikAdapter
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.presenter.pasien.daftar.PencarianKlinikPresenter
import id.calvintd.klinikkita.view.pasien.daftar.PencarianKlinikView

class PencarianKlinikActivity : AppCompatActivity(), PencarianKlinikView {
    private lateinit var progressBar: ProgressBar
    private lateinit var txtKosong: TextView
    private lateinit var rvKlinik: RecyclerView

    private val presenter = PencarianKlinikPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian_klinik)

        progressBar = findViewById(R.id.pbPencarianKlinik)
        txtKosong = findViewById(R.id.txtPencarianKlinikKosong)
        rvKlinik = findViewById(R.id.rvPencarianKlinik)

        presenter.cekDokter()
    }

    override fun dokterKlinikKosong() {
        progressBar.visibility = View.GONE
        txtKosong.visibility = View.VISIBLE
        rvKlinik.visibility = View.GONE

        rvKlinik.layoutManager = LinearLayoutManager(this)
        rvKlinik.adapter = PencarianKlinikAdapter(listOf(), listOf()) { _, _ ->

        }
    }

    override fun dokterTersedia(keyKlinikList: List<String>) {
        txtKosong.visibility = View.GONE
        presenter.cekKlinik(keyKlinikList)
    }

    override fun tampilDaftarKlinik(pairKlinik: List<Pair<String, Klinik>>) {
        val pairKlinikUnzip = pairKlinik.unzip()

        val keyKlinikList = pairKlinikUnzip.first
        val listKlinik = pairKlinikUnzip.second

        progressBar.visibility = View.GONE
        rvKlinik.visibility = View.VISIBLE
        rvKlinik.layoutManager = LinearLayoutManager(this)
        rvKlinik.adapter =
            PencarianKlinikAdapter(keyKlinikList, listKlinik) { key: String, namaKlinik: String ->
                startActivity(
                    Intent(this, PencarianDokterActivity::class.java)
                        .putExtra(resources.getString(R.string.extras_key_clinic), key)
                        .putExtra(resources.getString(R.string.extras_name_clinic), namaKlinik)
                )
            }
    }
}
