package id.calvintd.klinikkita.adapter.pasien.pemeriksaan

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.PendaftaranPasienInternal

class CekPendaftaranAdapter(private val listPendaftaranPasien: List<PendaftaranPasienInternal>) :
    RecyclerView.Adapter<CekPendaftaranAdapter.PendaftaranViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendaftaranViewHolder {
        val itemRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pasien_cek_pendaftaran, parent, false)
        return PendaftaranViewHolder(itemRow)
    }

    override fun getItemCount() = listPendaftaranPasien.size

    override fun onBindViewHolder(holder: PendaftaranViewHolder, position: Int) = holder.bind(
        listPendaftaranPasien[position]
    )

    class PendaftaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtNamaPasien: TextView? = null
        private var txtNamaKlinik: TextView? = null
        private var txtNamaDokter: TextView? = null
        private var txtIsiKeluhan: TextView? = null
        private var txtIsiStatus: TextView? = null

        init {
            txtNamaPasien = itemView.findViewById(R.id.txtNamaPasienCekPendaftaran)
            txtNamaKlinik = itemView.findViewById(R.id.txtNamaKlinikCekPendaftaran)
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterCekPendaftaran)
            txtIsiKeluhan = itemView.findViewById(R.id.txtIsiKeluhanCekPendaftaran)
            txtIsiStatus = itemView.findViewById(R.id.txtIsiStatusCekPendaftaran)
        }

        fun bind(pendaftaranPasien: PendaftaranPasienInternal) {
            txtNamaPasien?.text = pendaftaranPasien.namaPasien
            txtNamaKlinik?.text = pendaftaranPasien.namaKlinik
            txtNamaDokter?.text = pendaftaranPasien.namaDokter
            txtIsiKeluhan?.text = pendaftaranPasien.keluhan
            val res = txtIsiStatus?.context?.resources

            res?.let {
                if (pendaftaranPasien.status != 0) {
                    txtIsiStatus?.setTypeface(txtIsiStatus?.typeface, Typeface.BOLD)
                    if (pendaftaranPasien.status == -1) {
                        txtIsiStatus?.setTextColor(Color.RED)
                        txtIsiStatus?.text =
                            res.getString(R.string.patient_appointments_list_current_status_no_show)
                    } else {
                        txtIsiStatus?.setTextColor(Color.GREEN)
                        txtIsiStatus?.text =
                            res.getString(R.string.patient_appointments_list_current_status_show)
                    }
                } else {
                    txtIsiStatus?.text =
                        res.getString(R.string.patient_appointments_list_current_status_pending)
                }
            }
        }
    }
}