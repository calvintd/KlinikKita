package id.calvintd.klinikkita.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {
    const val utc = 7L
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("EE, d MMMM uuuu, HH:mm:ss")

    fun konversi(timeInMillis: Long): String {
        val date = Date(timeInMillis)

        formatter.timeZone = TimeZone.getDefault()
        
        return formatter.format(date)
    }
}