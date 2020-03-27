package id.calvintd.klinikkita.itemmodel.internal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PemeriksaanInternal(
    var idPemeriksaan: String?,
    var waktu: Long?,
    var diagnosis: String?,
    var pengobatan: String?,
    var idPendaftaran: String?,
    var keluhan: String?,
    var idPasien: String?,
    var namaPasien: String?,
    var idDokter: String?,
    var namaDokter: String?,
    var idKlinik: String?,
    var namaKlinik: String?
): Parcelable