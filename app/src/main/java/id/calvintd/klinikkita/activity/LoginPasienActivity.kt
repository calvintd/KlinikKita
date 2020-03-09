package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R

class LoginPasienActivity : AppCompatActivity() {
    private lateinit var btnMasuk: Button
    private lateinit var txtDaftar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_pasien)

        btnMasuk = findViewById(R.id.btnLoginPasien)
        txtDaftar = findViewById(R.id.txtDaftarPasien)

        btnMasuk.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }

        txtDaftar.setOnClickListener {
            Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }
    }
}
