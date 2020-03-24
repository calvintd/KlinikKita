package id.calvintd.klinikkita.itemmodel.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Klinik (var namaKlinik: String, var alamat: String, var kota: String, var nomorHP: String, var password: String): Parcelable