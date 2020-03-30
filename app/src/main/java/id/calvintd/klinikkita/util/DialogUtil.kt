package id.calvintd.klinikkita.util

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import id.calvintd.klinikkita.R

object DialogUtil {
    fun errorDialog(context: Context, resources: Resources) : AlertDialog.Builder {
        return AlertDialog.Builder(context)
            .setTitle(resources.getString(R.string.key_error_dialog_title))
            .setMessage(resources.getString(R.string.key_error_dialog_message))
    }
}