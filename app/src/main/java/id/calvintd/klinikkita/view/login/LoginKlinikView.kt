package id.calvintd.klinikkita.view.login

import id.calvintd.klinikkita.itemmodel.database.Klinik

interface LoginKlinikView: LoginView {
    fun loginSukses(key: String, dataKlinik: Klinik)
}