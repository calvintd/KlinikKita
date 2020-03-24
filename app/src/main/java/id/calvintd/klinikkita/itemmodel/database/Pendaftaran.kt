package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pendaftaran (var idPasien: String, var idDokter: String, var keluhan: String, var status: String): Parcelable