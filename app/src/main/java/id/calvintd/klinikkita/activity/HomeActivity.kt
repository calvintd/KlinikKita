package id.calvintd.klinikkita.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.klinik.BerandaKlinikActivity
import id.calvintd.klinikkita.activity.klinik.LoginKlinikActivity
import id.calvintd.klinikkita.activity.pasien.BerandaPasienActivity
import id.calvintd.klinikkita.activity.pasien.LoginPasienActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var imgPasien: ImageView
    private lateinit var imgKlinik: ImageView

    private val sharedPreferences =
        getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
    private val defaultKey = resources.getString(R.string.shared_pref_key_default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        imgPasien = findViewById(R.id.imgPasien)
        imgKlinik = findViewById(R.id.imgKlinik)

        if (sharedPreferences.getString(
                resources.getString(R.string.shared_pref_patient_key),
                defaultKey
            ) != defaultKey
        ) {
            startActivity(
                Intent(this, BerandaPasienActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        } else if (sharedPreferences.getString(
                resources.getString(R.string.shared_pref_clinic_key),
                defaultKey
            ) != defaultKey
        ) {
            startActivity(
                Intent(this, BerandaKlinikActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

        imgPasien.setOnClickListener {
            startActivity(Intent(this, LoginPasienActivity::class.java))
        }

        imgKlinik.setOnClickListener {
            startActivity(Intent(this, LoginKlinikActivity::class.java))
        }
    }
}
