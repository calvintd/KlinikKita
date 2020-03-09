package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
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
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }

        imgKlinik.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }
    }
}
