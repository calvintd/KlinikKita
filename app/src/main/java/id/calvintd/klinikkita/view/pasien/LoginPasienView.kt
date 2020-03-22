package id.calvintd.klinikkita.view.pasien

import id.calvintd.klinikkita.itemmodel.database.Pasien
import id.calvintd.klinikkita.view.LoginView

interface LoginPasienView: LoginView {
    fun loginSukses(key: String, dataPasien: Pasien)
}