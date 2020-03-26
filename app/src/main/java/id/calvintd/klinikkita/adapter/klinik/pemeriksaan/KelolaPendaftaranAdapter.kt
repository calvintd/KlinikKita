package id.calvintd.klinikkita.adapter.klinik.pemeriksaan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.PendaftaranKlinikInternal

class KelolaPendaftaranAdapter(
    private val listPendaftaranKlinik: List<PendaftaranKlinikInternal>,
    private val hadirListener: (PendaftaranKlinikInternal) -> Unit,
    private val tidakHadirListener: (String) -> Unit
) : RecyclerView.Adapter<KelolaPendaftaranAdapter.PendaftaranViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendaftaranViewHolder {
        val itemRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_klinik_kelola_pendaftaran, parent, false)
        return PendaftaranViewHolder(itemRow)
    }

    override fun getItemCount() = listPendaftaranKlinik.size

    override fun onBindViewHolder(holder: PendaftaranViewHolder, position: Int) = holder.bind(
        listPendaftaranKlinik[position], hadirListener, tidakHadirListener
    )

    class PendaftaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtNamaPasien: TextView? = null
        private var txtNamaKlinik: TextView? = null
        private var txtNamaDokter: TextView? = null
        private var txtIsiKeluhan: TextView? = null
        private var imgHadir: ImageView? = null
        private var imgTidakHadir: ImageView? = null

        init {
            txtNamaPasien = itemView.findViewById(R.id.txtNamaPasienKelolaPemeriksaan)
            txtNamaKlinik = itemView.findViewById(R.id.txtNamaKlinikKelolaPemeriksaan)
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterKelolaPemeriksaan)
            txtIsiKeluhan = itemView.findViewById(R.id.txtIsiKeluhanKelolaPemeriksaan)
            imgHadir = itemView.findViewById(R.id.imgHadirKelolaPemeriksaan)
            imgTidakHadir = itemView.findViewById(R.id.imgTidakHadirKelolaPemeriksaan)
        }

        fun bind(
            pendaftaranKlinik: PendaftaranKlinikInternal,
            hadirListener: (pendaftaran: PendaftaranKlinikInternal) -> Unit,
            tidakHadirListener: (key: String) -> Unit
        ) {
            txtNamaPasien?.text = pendaftaranKlinik.namaPasien
            txtNamaKlinik?.text = pendaftaranKlinik.namaKlinik
            txtNamaDokter?.text = pendaftaranKlinik.namaDokter
            txtIsiKeluhan?.text = pendaftaranKlinik.keluhan
            imgHadir?.setOnClickListener {
                hadirListener(pendaftaranKlinik)
            }
            imgTidakHadir?.setOnClickListener {
                pendaftaranKlinik.idPendaftaran?.let { it1 -> tidakHadirListener(it1) }
            }
        }
    }
}