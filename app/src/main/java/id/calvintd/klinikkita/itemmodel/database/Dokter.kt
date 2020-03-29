package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Keep
@IgnoreExtraProperties
@Parcelize
data class Dokter(
    var idKlinik: String = "",
    var namaDokter: String = "",
    var deskripsi: String = ""
) : Parcelable {

}