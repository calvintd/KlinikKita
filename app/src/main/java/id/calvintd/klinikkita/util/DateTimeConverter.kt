package id.calvintd.klinikkita.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {
    const val utc = 7L
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("EEEE, d MMMM YYYY, HH:mm")

    fun konversi(timeInMillis: Long): String {
        val date = Date(timeInMillis)

        formatter.timeZone = TimeZone.getDefault()
        
        return formatter.format(date)
    }
}