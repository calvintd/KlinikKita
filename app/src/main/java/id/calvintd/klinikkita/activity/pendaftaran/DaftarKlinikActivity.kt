package id.calvintd.klinikkita.activity.pendaftaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.activity.login.LoginActivity
import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.itemmodel.internal.Kolom
import id.calvintd.klinikkita.presenter.pendaftaran.DaftarKlinikPresenter
import id.calvintd.klinikkita.view.PendaftaranView

class DaftarKlinikActivity : AppCompatActivity(), PendaftaranView {
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

    private lateinit var presenter: DaftarKlinikPresenter

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

        cbPersetujuan = findViewById(R.id.cbPersetujuanKlinik)

        btnDaftar = findViewById(R.id.btnDaftarKlinik)

        btnDaftar.setOnClickListener {
            val namaKlinik = edtNamaKlinik.text.toString()
            val alamat = edtAlamat.text.toString()
            val kota = edtKota.text.toString()
            val nomorHP = edtNomorHP.text.toString()
            val kataSandi = edtKataSandi.text.toString()
            val kataSandiUlang = edtUlangKataSandi.text.toString()
            val persetujuan = cbPersetujuan.isChecked

            val daftarKolom = listOf(
                Kolom(
                    namaKlinik,
                    txtKesalahanNamaKlinik,
                    resources.getString(R.string.clinic_empty_name)
                ),
                Kolom(alamat, txtKesalahanAlamat, resources.getString(R.string.key_empty_address)),
                Kolom(kota, txtKesalahanKota, resources.getString(R.string.key_empty_city)),
                Kolom(nomorHP, txtKesalahanNomorHP, resources.getString(R.string.key_empty_phone)),
                Kolom(
                    kataSandi,
                    txtKesalahanKataSandi,
                    resources.getString(R.string.key_empty_password)
                ),
                Kolom(
                    kataSandiUlang,
                    txtKesalahanUlangKataSandi,
                    resources.getString(R.string.key_empty_repeat_password)
                )
            )

            val dataKlinik = Klinik(
                namaKlinik = namaKlinik,
                alamat = alamat,
                kota = kota,
                nomorHP = nomorHP,
                password = kataSandi
            )

            presenter =
                DaftarKlinikPresenter(
                    this,
                    dataKlinik
                )

            presenter.cekFormulir(
                daftarKolom = daftarKolom,
                txtNomorHP = txtKesalahanNomorHP,
                kataSandiUlang = kataSandiUlang,
                txtKataSandiUlang = txtKesalahanUlangKataSandi,
                persetujuan = persetujuan,
                txtPersetujuan = txtKesalahanPersetujuan
            )
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
        setelTeksKesalahan(
            teksKesalahan,
            resources.getString(R.string.clinic_register_existing_phone)
        )
    }

    override fun passwordUlangSalah(teksKesalahan: TextView) {
        setelTeksKesalahan(teksKesalahan, resources.getString(R.string.key_wrong_repeat_password))
    }

    override fun persetujuanDitolak(teksKesalahan: TextView) {
        setelTeksKesalahan(
            teksKesalahan,
            resources.getString(R.string.clinic_terms_of_use_unchecked)
        )
    }

    override fun setelTeksKesalahan(teksKesalahan: TextView, teks: String) {
        teksKesalahan.visibility = View.VISIBLE
        teksKesalahan.text = teks
    }

    override fun sembunyikanTeksKesalahan(teksKesalahan: TextView) {
        teksKesalahan.visibility = View.GONE
    }

    override fun pendaftaranSukses() {
        Toast.makeText(this, R.string.key_successful_registration, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
