package id.calvintd.klinikkita.view.klinik

import id.calvintd.klinikkita.itemmodel.database.Klinik
import id.calvintd.klinikkita.view.LoginView

interface LoginKlinikView: LoginView {
    fun loginSukses(key: String, dataKlinik: Klinik)
}