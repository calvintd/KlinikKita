package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Keep
@IgnoreExtraProperties
@Parcelize
data class Pendaftaran(
    var idPasien: String = "",
    var idDokter: String = "",
    var keluhan: String = "",
    var status: Int = 0
) : Parcelable