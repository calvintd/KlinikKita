package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.util.CekFormulirPendaftaran

class DaftarKlinikActivity : AppCompatActivity() {
    // EditText
    private lateinit var edtNamaKlinik: EditText
    private lateinit var edtAlamat: EditText
    private lateinit var edtKota: EditText
    private lateinit var edtNomorHP: EditText
    private lateinit var edtKataSandi: EditText
    private lateinit var edtUlangKataSandi: EditText

    // TextView kesalahan
    private lateinit var txtKesalahanNamaKlinik: TextView
    private lateinit var txtKesalahanAlamat: TextView
    private lateinit var txtKesalahanKota: TextView
    private lateinit var txtKesalahanNomorHP: TextView
    private lateinit var txtKesalahanKataSandi: TextView
    private lateinit var txtKesalahanUlangKataSandi: TextView
    private lateinit var txtKesalahanPersetujuan: TextView

    private lateinit var cbPersetujuan: CheckBox

    private lateinit var btnDaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_klinik)

        // EditText
        edtNamaKlinik = findViewById(R.id.edtNamaKlinik)
        edtAlamat = findViewById(R.id.edtAlamatKlinik)
        edtKota = findViewById(R.id.edtKotaKlinik)
        edtNomorHP = findViewById(R.id.edtNomorHPKlinik)
        edtKataSandi = findViewById(R.id.edtKataSandiKlinik)
        edtUlangKataSandi = findViewById(R.id.edtUlangKataSandiKlinik)

        // TextView kesalahan
        txtKesalahanNamaKlinik = findViewById(R.id.txtKesalahanNamaKlinik)
        txtKesalahanAlamat = findViewById(R.id.txtKesalahanAlamatKlinik)
        txtKesalahanKota = findViewById(R.id.txtKesalahanKotaKlinik)
        txtKesalahanNomorHP = findViewById(R.id.txtKesalahanNomorHPKlinik)
        txtKesalahanKataSandi = findViewById(R.id.txtKesalahanKataSandiKlinik)
        txtKesalahanUlangKataSandi = findViewById(R.id.txtKesalahanUlangKataSandiKlinik)
        txtKesalahanPersetujuan = findViewById(R.id.txtKesalahanPersetujuanKlinik)

        val daftarKolom = listOf(
            Kolom(edtNamaKlinik, txtKesalahanNamaKlinik, resources.getString(R.string.clinic_empty_name)),
            Kolom(edtAlamat, txtKesalahanAlamat, resources.getString(R.string.key_empty_address)),
            Kolom(edtKota, txtKesalahanKota, resources.getString(R.string.key_empty_city)),
            Kolom(edtNomorHP, txtKesalahanNomorHP, resources.getString(R.string.key_empty_phone)),
            Kolom(edtKataSandi, txtKesalahanKataSandi, resources.getString(R.string.key_empty_password)),
            Kolom(edtUlangKataSandi, txtKesalahanUlangKataSandi, resources.getString(R.string.key_empty_repeat_password))
        )

        cbPersetujuan = findViewById(R.id.cbPersetujuanKlinik)

        btnDaftar = findViewById(R.id.btnDaftarPasien)

        val cek = CekFormulirPendaftaran

        btnDaftar.setOnClickListener {

        }
    }
}
