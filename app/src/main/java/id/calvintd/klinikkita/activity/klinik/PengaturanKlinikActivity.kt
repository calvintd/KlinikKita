package id.calvintd.klinikkita.activity.klinik

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.presenter.klinik.PengaturanKlinikPresenter
import id.calvintd.klinikkita.view.PengaturanView

class PengaturanKlinikActivity : AppCompatActivity(), PengaturanView {
    private lateinit var txtNama: TextView
    private lateinit var txtAlamat: TextView
    private lateinit var txtKota: TextView
    private lateinit var txtNomorHP: TextView
    private lateinit var edtLama: EditText
    private lateinit var txtSalahLama: TextView
    private lateinit var edtBaru: EditText
    private lateinit var txtSalahBaru: TextView
    private lateinit var edtUlangBaru: EditText
    private lateinit var txtSalahUlangBaru: TextView
    private lateinit var btnUbah: Button

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var defaultKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan_klinik)

        sharedPreferences = getSharedPreferences(getString(R.string.key_shared_pref), Context.MODE_PRIVATE)
        defaultKey = resources.getString(R.string.shared_pref_key_default)

        txtNama = findViewById(R.id.txtNamaPengaturanKlinik)
        txtAlamat = findViewById(R.id.txtAlamatPengaturanKlinik)
        txtKota = findViewById(R.id.txtKotaPengaturanKlinik)
        txtNomorHP = findViewById(R.id.txtNomorHPPengaturanKlinik)
        edtLama = findViewById(R.id.edtKataSandiLamaPengaturanKlinik)
        txtSalahLama = findViewById(R.id.txtKesalahanKataSandiLamaPengaturanKlinik)
        edtBaru = findViewById(R.id.edtKataSandiBaruPengaturanKlinik)
        txtSalahBaru = findViewById(R.id.txtKesalahanKataSandiBaruPengaturanKlinik)
        edtUlangBaru = findViewById(R.id.edtUlangKataSandiBaruPengaturanKlinik)
        txtSalahUlangBaru = findViewById(R.id.txtKesalahanUlangKataSandiBaruPengaturanKlinik)
        btnUbah = findViewById(R.id.btnUbahKataSandiKlinik)

        val idKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_key),
            defaultKey
        )
        val kataSandiKlinik = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_password),
            defaultKey
        )

        txtNama.text = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_name),
            defaultKey
        )
        txtAlamat.text = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_address),
            defaultKey
        )
        txtKota.text = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_city),
            defaultKey
        )
        txtNomorHP.text = sharedPreferences.getString(
            resources.getString(R.string.shared_pref_clinic_phone_number),
            defaultKey
        )

        val presenter = PengaturanKlinikPresenter(this)

        btnUbah.setOnClickListener {
            val kataSandiLama = edtLama.text.toString()
            val kataSandiBaru = edtBaru.text.toString()
            val kataSandiUlangBaru = edtUlangBaru.text.toString()

            val builder = AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.settings_change_password_dialog_title))
                .setMessage(resources.getString(R.string.settings_change_password_dialog_message))
                .setPositiveButton(resources.getString(R.string.key_yes)) { _, _ ->
                    idKlinik?.let { it1 -> presenter.ubahPassword(it1, kataSandiLama, kataSandiBaru, kataSandiUlangBaru) }
                }
                .setNegativeButton(resources.getString(R.string.key_no)) { _, _ ->

                }
                .show()
        }
    }

    override fun ubahKataSandi(kataSandiBaru: String) {
        sharedPreferences.edit()
            .putString(resources.getString(R.string.shared_pref_clinic_password), kataSandiBaru)
            .apply()
        Toast.makeText(this, R.string.settings_password_changed_toast, Toast.LENGTH_LONG).show()
        edtLama.text.clear()
        edtBaru.text.clear()
        edtUlangBaru.text.clear()
    }

    override fun cekKolomPasswordLama() {
        cekKosong(edtLama, txtSalahLama, resources.getString(R.string.settings_old_password_empty))
    }

    override fun cekKolomPasswordBaru() {
        cekKosong(edtBaru, txtSalahBaru, resources.getString(R.string.settings_new_password_empty))
    }

    override fun cekKolomUlangPasswordBaru() {
        cekKosong(edtUlangBaru, txtSalahUlangBaru, resources.getString(R.string.settings_repeat_new_password_empty))
    }

    override fun cekPasswordLama(sukses: Boolean) {
        cekSalah(edtLama, txtSalahLama, resources.getString(R.string.settings_old_password_wrong), sukses)
    }

    override fun cekUlangPasswordBaru(sukses: Boolean) {
        cekSalah(edtUlangBaru, txtSalahUlangBaru, resources.getString(R.string.settings_repeat_new_password_wrong), sukses)
    }

    private fun cekKosong(kolom: EditText, teksKesalahan: TextView, pesan: String) {
        if (kolom.text.isEmpty()) {
            teksKesalahan.visibility = View.VISIBLE
            teksKesalahan.text = pesan
        } else {
            teksKesalahan.visibility = View.GONE
        }
    }

    private fun cekSalah(kolom: EditText, teksKesalahan: TextView, pesan: String, sukses: Boolean) {
        if (kolom.text.isNotEmpty()) {
            if (!sukses) {
                teksKesalahan.visibility = View.VISIBLE
                teksKesalahan.text = pesan
            } else {
                teksKesalahan.visibility = View.GONE
            }
        }
    }
}
