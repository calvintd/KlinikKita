package id.calvintd.klinikkita.itemmodel.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PendaftaranPasienInternal(
    var idPendaftaran: String?,
    var keluhan: String?,
    var status: Int?,
    var idPasien: String?,
    var namaPasien: String?,
    var idKlinik: String?,
    var namaKlinik: String?,
    var idDokter: String?,
    var namaDokter: String?
) : Parcelable