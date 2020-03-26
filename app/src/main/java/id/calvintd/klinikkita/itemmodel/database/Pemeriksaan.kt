package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pemeriksaan (var idPendaftaran: String, var waktu: Long, var diagnosis: String, var pengobatan: String): Parcelable