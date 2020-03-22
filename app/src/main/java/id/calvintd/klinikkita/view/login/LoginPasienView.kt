package id.calvintd.klinikkita.view.login

import id.calvintd.klinikkita.itemmodel.database.Pasien

interface LoginPasienView: LoginView {
    fun loginSukses(key: String, dataPasien: Pasien)
}