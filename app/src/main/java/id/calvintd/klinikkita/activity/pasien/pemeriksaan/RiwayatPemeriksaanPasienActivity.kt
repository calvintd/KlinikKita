package id.calvintd.klinikkita.activity.pasien.pemeriksaan

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.PemeriksaanInternal

class RiwayatPemeriksaanPasienActivity : AppCompatActivity() {
    private lateinit var txtPemeriksaanKosong: TextView
    private lateinit var rvPemeriksaanPasien: RecyclerView

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)
    private lateinit var idPasien: String

    private val listPemeriksaanPasien = mutableListOf<PemeriksaanInternal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_pemeriksaan_pasien)

        txtPemeriksaanKosong = findViewById(R.id.txtRiwayatPemeriksaanPasienKosong)
        rvPemeriksaanPasien = findViewById(R.id.rvRiwayatPemeriksaanPasien)

        idPasien = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_patient_key),
            defaultKey
        ).toString()
    }
}
