package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.view.klinik.pemeriksaan.DiagnosisPemeriksaanView

class DiagnosisPemeriksaanActivity : AppCompatActivity(), DiagnosisPemeriksaanView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis_pemeriksaan)
    }
}
