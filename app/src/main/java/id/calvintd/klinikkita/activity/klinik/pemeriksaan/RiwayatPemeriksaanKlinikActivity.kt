package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.view.klinik.pemeriksaan.RiwayatPemeriksaanKlinikView

class RiwayatPemeriksaanKlinikActivity : AppCompatActivity(), RiwayatPemeriksaanKlinikView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_pemeriksaan_klinik)
    }
}
