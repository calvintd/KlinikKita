package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R

class DaftarPasienActivity : AppCompatActivity() {
    private lateinit var edtNomorHP: EditText
    private lateinit var txtKesalahan: TextView
    private lateinit var btnDaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pasien)

        edtNomorHP = findViewById(R.id.edtNomorHPPasien)
        txtKesalahan = findViewById(R.id.txtKesalahanNomorHPDaftarPasien)
        btnDaftar = findViewById(R.id.btnDaftarPasien)

        btnDaftar.setOnClickListener {
            val nomorHP = edtNomorHP.text.toString()

            if(!cekFormatNomorHP(nomorHP)) {
                txtKesalahan.visibility = View.VISIBLE
                txtKesalahan.text = resources.getString(R.string.key_phone_wrong_formatting)
            }
            else {
                if(txtKesalahan.visibility == View.VISIBLE) txtKesalahan.visibility = View.GONE
                Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cekFormatNomorHP(nomorHP: String) : Boolean {
        return nomorHP.startsWith("08")
    }
}
