package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Keep
@IgnoreExtraProperties
@Parcelize
data class Klinik(
    var namaKlinik: String = "",
    var alamat: String = "",
    var kota: String = "",
    var nomorHP: String = "",
    var password: String = ""
) : Parcelable