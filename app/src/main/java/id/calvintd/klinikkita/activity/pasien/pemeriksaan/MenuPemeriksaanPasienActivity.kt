package id.calvintd.klinikkita.activity.pasien.pemeriksaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import id.calvintd.klinikkita.R

class MenuPemeriksaanPasienActivity : AppCompatActivity() {
    private lateinit var imgCekPendaftaran: ImageView
    private lateinit var imgRiwayatPemeriksaan: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_pemeriksaan_pasien)

        imgCekPendaftaran = findViewById(R.id.imgCekPendaftaranPasien)
        imgRiwayatPemeriksaan = findViewById(R.id.imgRiwayatPemeriksaanPasien)

        imgCekPendaftaran.setOnClickListener {
            startActivity(Intent(this, CekPendaftaranActivity::class.java))
        }

        imgRiwayatPemeriksaan.setOnClickListener {
            startActivity(Intent(this, RiwayatPemeriksaanPasienActivity::class.java))
        }
    }
}
