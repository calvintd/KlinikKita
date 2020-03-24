package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Pemeriksaan (var idPendaftaran: String, var tanggal: String, var diagnosis: String, var perawatan: String): Parcelable