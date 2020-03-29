package id.calvintd.klinikkita.itemmodel.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PendaftaranKlinikInternal(
    var idPendaftaran: String?,
    var keluhan: String?,
    var idPasien: String?,
    var namaPasien: String?,
    var idDokter: String?,
    var namaDokter: String?,
    var idKlinik: String?,
    var namaKlinik: String?
) : Parcelable
