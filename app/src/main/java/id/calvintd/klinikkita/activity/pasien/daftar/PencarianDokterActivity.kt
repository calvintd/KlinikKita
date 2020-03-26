package id.calvintd.klinikkita.activity.pasien.daftar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.adapter.pasien.daftar.PencarianDokterAdapter
import id.calvintd.klinikkita.itemmodel.database.Dokter
import id.calvintd.klinikkita.presenter.pasien.daftar.PencarianDokterPresenter
import id.calvintd.klinikkita.view.pasien.daftar.PencarianDokterView

class PencarianDokterActivity : AppCompatActivity(), PencarianDokterView {
    private lateinit var rvDokter: RecyclerView

    private val bundle: Bundle? = intent.extras
    private lateinit var keyKlinik: String
    private lateinit var namaKlinik: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pencarian_dokter)

        rvDokter = findViewById(R.id.rvPencarianDokter)

        keyKlinik = bundle?.getString(resources.getString(R.string.extras_key_clinic)).toString()
        namaKlinik = bundle?.getString(resources.getString(R.string.extras_name_clinic)).toString()

        val presenter = PencarianDokterPresenter(this)

        presenter.tampilDokter(keyKlinik)
    }

    override fun tampilDokter(pairDokter: List<Pair<String, Dokter>>) {
        val pairDokterUnzip = pairDokter.unzip()

        val keyDokterList = pairDokterUnzip.first
        val listDokter = pairDokterUnzip.second

        rvDokter.layoutManager = LinearLayoutManager(this)
        rvDokter.adapter = PencarianDokterAdapter(keyDokterList, listDokter) { key: String, namaDokter: String ->
            startActivity(Intent(this, EntriKeluhanActivity::class.java)
                .putExtra(resources.getString(R.string.extras_name_clinic), namaKlinik)
                .putExtra(resources.getString(R.string.extras_key_doctor), key)
                .putExtra(resources.getString(R.string.extras_name_doctor), namaDokter))
        }
    }
}
