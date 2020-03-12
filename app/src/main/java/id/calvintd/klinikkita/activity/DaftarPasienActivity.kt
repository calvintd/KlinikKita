package id.calvintd.klinikkita.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.presenter.DaftarPasienPresenter
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarPasienActivity : AppCompatActivity(), PendaftaranView {
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

        val namaLengkap = edtNamaLengkap.text.toString()
        val namaPanggilan = edtNamaPanggilan.text.toString()
        val alamat = edtAlamat.text.toString()
        val kota = edtKota.text.toString()
        val nomorHP = edtNomorHP.text.toString()
        val kataSandi = edtKataSandi.text.toString()
        val kataSandiUlang = edtUlangKataSandi.text.toString()

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
            Kolom(namaLengkap, txtKesalahanNamaLengkap, resources.getString(R.string.patient_empty_full_name)),
            Kolom(namaPanggilan, txtKesalahanNamaPanggilan, resources.getString(R.string.patient_empty_nickname)),
            Kolom(alamat, txtKesalahanAlamat, resources.getString(R.string.key_empty_address)),
            Kolom(kota, txtKesalahanKota, resources.getString(R.string.key_empty_city)),
            Kolom(nomorHP, txtKesalahanNomorHP, resources.getString(R.string.key_empty_phone)),
            Kolom(kataSandi, txtKesalahanKataSandi, resources.getString(R.string.key_empty_password)),
            Kolom(kataSandiUlang, txtKesalahanUlangKataSandi, resources.getString(R.string.key_empty_repeat_password))
        )

        cbPersetujuan = findViewById(R.id.cbPersetujuanPasien)

        btnDaftar = findViewById(R.id.btnDaftarPasien)

        val dataPasien = Pasien(
            namaLengkap = namaLengkap, namaPanggilan = namaPanggilan, alamat = alamat, kota = kota, nomorHP = nomorHP, password = kataSandi
        )

        val presenter = DaftarPasienPresenter(this, dataPasien)

        btnDaftar.setOnClickListener {
            val persetujuan = cbPersetujuan.isChecked

            if(presenter.cekFormulir(daftarKolom = daftarKolom,
                txtNomorHP = txtKesalahanNomorHP,
                kataSandiUlang = kataSandiUlang,
                txtKataSandiUlang = txtKesalahanUlangKataSandi,
                persetujuan = persetujuan,
                txtPersetujuan = txtKesalahanPersetujuan)) {
                Toast.makeText(this, R.string.test, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun kolomKosong(teksKesalahan: TextView, teks: String) {
        setelTeksKesalahan(teksKesalahan, teks)
    }

    override fun nomorHPSalahFormat(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.key_phone_wrong_formatting))
    }

    override fun nomorHPTerlaluPendek(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.key_phone_too_short))
    }

    override fun nomorHPTerdaftar(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.patient_register_existing_phone))
    }

    override fun passwordUlangSalah(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.key_wrong_repeat_password))
    }

    override fun persetujuanDitolak(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.patient_privacy_policy_unchecked))
    }

    override fun setelTeksKesalahan(teksKesalahan: TextView, teks: String) {
        teksKesalahan.visibility = View.VISIBLE
        teksKesalahan.text = teks
    }

    override fun sembunyikanTeksKesalahan(teksKesalahan: TextView) {
        teksKesalahan.visibility = View.GONE
    }
}
