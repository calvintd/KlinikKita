package id.calvintd.klinikkita.presenter.login

import id.calvintd.klinikkita.view.LoginView

class LoginKlinikPresenter(private val loginView: LoginView) {
    fun login(nomorHP: String, kataSandi: String) {
        if (nomorHP.isEmpty() || kataSandi.isEmpty()) {
            loginView.kolomKosong()
        } else {

        }
    }
}