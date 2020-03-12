package id.calvintd.klinikkita.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R

class LoginKlinikActivity : AppCompatActivity() {
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_klinik)

        btnMasuk = findViewById(R.id.btnLoginKlinik)
        txtDaftar = findViewById(R.id.txtDaftarKlinik)

        btnMasuk.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }

        txtDaftar.setOnClickListener {
            startActivity(Intent(this, DaftarKlinikActivity::class.java))
        }
    }
}
