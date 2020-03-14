package id.calvintd.klinikkita.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import id.calvintd.klinikkita.R

class LoginActivity : AppCompatActivity() {
    private lateinit var imgPasien: ImageView
    private lateinit var imgKlinik: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        imgPasien = findViewById(R.id.imgPasien)
        imgKlinik = findViewById(R.id.imgKlinik)

        imgPasien.setOnClickListener {
            startActivity(Intent(this, LoginPasienActivity::class.java))
        }

        imgKlinik.setOnClickListener {
            startActivity(Intent(this, LoginKlinikActivity::class.java))
        }
    }
}
