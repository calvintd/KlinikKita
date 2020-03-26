package id.calvintd.klinikkita.activity.klinik.pemeriksaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import id.calvintd.klinikkita.R

class MenuPemeriksaanKlinikActivity : AppCompatActivity() {
    private lateinit var imgKelolaPendaftaran: ImageView
    private lateinit var imgRiwayatPemeriksaan: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pemeriksaan_klinik)

        imgKelolaPendaftaran = findViewById(R.id.imgKelolaPemeriksaanKlinik)
        imgRiwayatPemeriksaan = findViewById(R.id.imgRiwayatPemeriksaanKlinik)

        imgKelolaPendaftaran.setOnClickListener {
            startActivity(Intent(this, KelolaPendaftaranActivity::class.java))
        }

        imgRiwayatPemeriksaan.setOnClickListener {
            startActivity(Intent(this, RiwayatPemeriksaanKlinikActivity::class.java))
        }
    }
}
