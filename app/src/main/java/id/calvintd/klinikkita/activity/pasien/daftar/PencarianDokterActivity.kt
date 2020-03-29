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
import id.calvintd.klinikkita.adapter.pasien.daftar.PencarianDokterAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.presenter.pasien.daftar.PencarianDokterPresenter
import id.calvintd.klinikkita.view.pasien.daftar.PencarianDokterView

class PencarianDokterActivity : AppCompatActivity(), PencarianDokterView {
    private lateinit var progressBar: ProgressBar
    private lateinit var txtNamaKlinik: TextView
    private lateinit var rvDokter: RecyclerView

    private var bundle: Bundle? = null
    private lateinit var keyKlinik: String
    private lateinit var namaKlinik: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian_dokter)

        progressBar = findViewById(R.id.pbPencarianDokter)
        txtNamaKlinik = findViewById(R.id.txtNamaKlinikPemeriksaanPasien)
        rvDokter = findViewById(R.id.rvPencarianDokter)

        bundle = intent.extras

        keyKlinik = bundle?.getString(resources.getString(R.string.extras_key_clinic)).toString()
        namaKlinik = bundle?.getString(resources.getString(R.string.extras_name_clinic)).toString()

        txtNamaKlinik.text = namaKlinik

        val presenter = PencarianDokterPresenter(this)

        presenter.tampilDokter(keyKlinik)
    }

    override fun tampilDokter(pairDokter: List<Pair<String, Dokter>>) {
        val pairDokterUnzip = pairDokter.unzip()

        val keyDokterList = pairDokterUnzip.first
        val listDokter = pairDokterUnzip.second

        progressBar.visibility = View.GONE
        rvDokter.visibility = View.VISIBLE
        rvDokter.layoutManager = LinearLayoutManager(this)
        rvDokter.adapter = PencarianDokterAdapter(keyDokterList, listDokter) { key: String, namaDokter: String ->
            startActivity(Intent(this, EntriKeluhanActivity::class.java)
                .putExtra(resources.getString(R.string.extras_name_clinic), namaKlinik)
                .putExtra(resources.getString(R.string.extras_key_doctor), key)
                .putExtra(resources.getString(R.string.extras_name_doctor), namaDokter))
        }
    }
}
