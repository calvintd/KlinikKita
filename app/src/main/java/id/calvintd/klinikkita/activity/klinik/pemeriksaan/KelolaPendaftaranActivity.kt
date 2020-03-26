package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.view.klinik.pemeriksaan.KelolaPendaftaranView

class KelolaPendaftaranActivity : AppCompatActivity(), KelolaPendaftaranView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_pendaftaran)
    }
}
