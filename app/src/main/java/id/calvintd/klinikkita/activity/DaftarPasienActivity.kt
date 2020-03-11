package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.util.CekFormulirPendaftaran

class DaftarPasienActivity : AppCompatActivity() {
    // EditText
    private lateinit var edtNamaLengkap: EditText
    private lateinit var edtNamaPanggilan: EditText
    private lateinit var edtAlamat: EditText
    private lateinit var edtKota: EditText
    private lateinit var edtNomorHP: EditText
    private lateinit var edtKataSandi: EditText
    private lateinit var edtUlangKataSandi: EditText

    // TextView kesalahan
    private lateinit var txtKesalahanNamaLengkap: TextView
    private lateinit var txtKesalahanNamaPanggilan: TextView
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
        setContentView(R.layout.activity_daftar_pasien)

        // EditText
        edtNamaLengkap = findViewById(R.id.edtNamaLengkapPasien)
        edtNamaPanggilan = findViewById(R.id.edtNamaPanggilanPasien)
        edtAlamat = findViewById(R.id.edtAlamatPasien)
        edtKota = findViewById(R.id.edtKotaPasien)
        edtNomorHP = findViewById(R.id.edtNomorHPPasien)
        edtKataSandi = findViewById(R.id.edtKataSandiPasien)
        edtUlangKataSandi = findViewById(R.id.edtUlangKataSandiPasien)

        // TextView kesalahan
        txtKesalahanNamaLengkap = findViewById(R.id.txtKesalahanNamaLengkapPasien)
        txtKesalahanNamaPanggilan = findViewById(R.id.txtKesalahanNamaPanggilanPasien)
        txtKesalahanAlamat = findViewById(R.id.txtKesalahanAlamatPasien)
        txtKesalahanKota = findViewById(R.id.txtKesalahanKotaPasien)
        txtKesalahanNomorHP = findViewById(R.id.txtKesalahanNomorHPPasien)
        txtKesalahanKataSandi = findViewById(R.id.txtKesalahanKataSandiPasien)
        txtKesalahanUlangKataSandi = findViewById(R.id.txtKesalahanUlangKataSandiPasien)
        txtKesalahanPersetujuan = findViewById(R.id.txtKesalahanPersetujuanPasien)

        val daftarKolom = listOf(
            Kolom(edtNamaLengkap, txtKesalahanNamaLengkap, resources.getString(R.string.patient_empty_full_name)),
            Kolom(edtNamaPanggilan, txtKesalahanNamaPanggilan, resources.getString(R.string.patient_empty_nickname)),
            Kolom(edtAlamat, txtKesalahanAlamat, resources.getString(R.string.key_empty_address)),
            Kolom(edtKota, txtKesalahanKota, resources.getString(R.string.key_empty_city)),
            Kolom(edtNomorHP, txtKesalahanNomorHP, resources.getString(R.string.key_empty_phone)),
            Kolom(edtKataSandi, txtKesalahanKataSandi, resources.getString(R.string.key_empty_password)),
            Kolom(edtUlangKataSandi, txtKesalahanUlangKataSandi, resources.getString(R.string.key_empty_repeat_password))
        )

        cbPersetujuan = findViewById(R.id.cbPersetujuanPasien)

        btnDaftar = findViewById(R.id.btnDaftarPasien)

        val cek = CekFormulirPendaftaran

        btnDaftar.setOnClickListener {
            val nomorHP = edtNomorHP.text.toString()
            var terisi = true
            var terformat = true
            var sesuai_panjang = true
            var terulang = true
            var tersetujui = true

            for (kolom in daftarKolom) {
                if (!cek.kolomTerisi(kolom)) terisi = false
            }

            if(edtNomorHP.text.isNotEmpty()) {
                if (!cek.formatNomorHP(nomorHP)) {
                    terformat = false
                    txtKesalahanNomorHP.visibility = View.VISIBLE
                    txtKesalahanNomorHP.text =
                        resources.getString(R.string.key_phone_wrong_formatting)
                } else if(!cek.panjangNomorHP(nomorHP)) {
                    sesuai_panjang = false
                    txtKesalahanNomorHP.visibility = View.VISIBLE
                    txtKesalahanNomorHP.text = resources.getString(R.string.key_phone_too_short)
                } else {
                    txtKesalahanNomorHP.visibility = View.GONE
                }
            }

            if(edtUlangKataSandi.text.isNotEmpty()) {
                if (!cek.ulangKataSandi(edtKataSandi, edtUlangKataSandi)) {
                    terulang = false
                    txtKesalahanUlangKataSandi.visibility = View.VISIBLE
                    txtKesalahanUlangKataSandi.text =
                        resources.getString(R.string.key_wrong_repeat_password)
                } else {
                    txtKesalahanUlangKataSandi.visibility = View.GONE
                }
            }

            if(!cbPersetujuan.isChecked) {
                tersetujui = false
                txtKesalahanPersetujuan.visibility = View.VISIBLE
                txtKesalahanPersetujuan.text = resources.getString(R.string.patient_privacy_policy_unchecked)
            } else {
                txtKesalahanPersetujuan.visibility = View.GONE
            }

            if(terisi && terformat && sesuai_panjang && terulang && tersetujui) Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
        }
    }
}
