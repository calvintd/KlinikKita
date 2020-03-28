package id.calvintd.klinikkita.activity.klinik

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.HomeActivity
import id.calvintd.klinikkita.activity.klinik.dokter.KelolaDokterActivity
import id.calvintd.klinikkita.activity.klinik.pemeriksaan.MenuPemeriksaanKlinikActivity

class BerandaKlinikActivity : AppCompatActivity() {
    private lateinit var txtSelamatDatang: TextView
    private lateinit var imgLihatDaftarRiwayat: ImageView
    private lateinit var imgKelolaDokter: ImageView
    private lateinit var imgPengaturan: ImageView
    private lateinit var imgKeluar: ImageView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_klinik)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtSelamatDatang = findViewById(R.id.txtSelamatDatangKlinik)
        imgLihatDaftarRiwayat = findViewById(R.id.imgLihatDaftarRiwayatKlinik)
        imgKelolaDokter = findViewById(R.id.imgKelolaDokterKlinik)
        imgPengaturan = findViewById(R.id.imgPengaturanBerandaKlinik)
        imgKeluar = findViewById(R.id.imgKeluarBerandaKlinik)

        if (sharedPreferences.getString(
                resources.getString(R.string.shared_pref_clinic_key),
                defaultKey
            ) ==
            defaultKey
        ) {
            startActivity(
                Intent(this, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

        txtSelamatDatang.text = resources.getString(
            R.string.clinic_home_header, sharedPreferences.getString(
                resources.getString(
                    R.string.shared_pref_clinic_name
                ), resources.getString(R.string.shared_pref_key_default)
            )
        )

        imgLihatDaftarRiwayat.setOnClickListener {
            startActivity(Intent(this, MenuPemeriksaanKlinikActivity::class.java))
        }

        imgKelolaDokter.setOnClickListener {
            startActivity(Intent(this, KelolaDokterActivity::class.java))
        }

        imgPengaturan.setOnClickListener {
            startActivity(Intent(this, PengaturanKlinikActivity::class.java))
        }

        imgKeluar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.key_logout_dialog_title))
                .setMessage(resources.getString(R.string.key_logout_dialog_message))
                .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                    sharedPreferences.edit()
                        .putString(resources.getString(R.string.shared_pref_clinic_key), defaultKey)
                        .putString(
                            resources.getString(R.string.shared_pref_clinic_name),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_clinic_address),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_clinic_city),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_clinic_phone_number),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_clinic_password),
                            defaultKey
                        )
                        .apply()
                    startActivity(
                        Intent(this, HomeActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }
                .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                }
                .setCancelable(false)
                .show()
        }
    }
}
