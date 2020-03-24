package id.calvintd.klinikkita.activity.pasien

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.HomeActivity

class BerandaPasienActivity : AppCompatActivity() {
    private lateinit var txtSelamatDatang: TextView
    private lateinit var imgDaftarPeriksa: ImageView
    private lateinit var imgLihatPemeriksaan: ImageView
    private lateinit var imgPengaturan: ImageView
    private lateinit var imgKeluar: ImageView

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_pasien)

        txtSelamatDatang = findViewById(R.id.txtSelamatDatangPasien)
        imgDaftarPeriksa = findViewById(R.id.imgDaftarPeriksaPasien)
        imgLihatPemeriksaan = findViewById(R.id.imgLihatPemeriksaanPasien)
        imgPengaturan = findViewById(R.id.imgPengaturanBerandaPasien)
        imgKeluar = findViewById(R.id.imgKeluarBerandaPasien)

        if (sharedPreferences.getString(
                resources.getString(R.string.shared_pref_patient_key),
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
            R.string.patient_home_greeting, sharedPreferences.getString(
                resources.getString(
                    R.string.shared_pref_patient_nickname
                ), resources.getString(R.string.shared_pref_key_default)
            )
        )

        imgDaftarPeriksa.setOnClickListener {

        }

        imgLihatPemeriksaan.setOnClickListener {

        }

        imgPengaturan.setOnClickListener {

        }

        imgKeluar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(resources.getString(R.string.key_logout_dialog_title))
                .setMessage(resources.getString(R.string.key_logout_dialog_message))
                .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                    sharedPreferences.edit()
                        .putString(resources.getString(R.string.shared_pref_patient_key), defaultKey)
                        .putString(
                            resources.getString(R.string.shared_pref_patient_full_name),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_patient_nickname),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_patient_address),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_patient_city),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_patient_phone_number),
                            defaultKey
                        )
                        .putString(
                            resources.getString(R.string.shared_pref_patient_password),
                            defaultKey
                        )
                        .apply()
                    startActivity(Intent(this, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
                .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                }
                .setCancelable(false)
                .show()
        }
    }
}
