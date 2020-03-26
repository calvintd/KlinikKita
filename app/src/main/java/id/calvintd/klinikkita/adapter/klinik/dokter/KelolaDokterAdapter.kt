package id.calvintd.klinikkita.adapter.klinik.dokter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Dokter

class KelolaDokterAdapter(
    private val listKeyDokter: List<String>,
    private val listDokter: List<Dokter>,
    private val ubahListener: (key: String, dokter: Dokter) -> Unit,
    private val hapusListener: (key: String) -> Unit
) : RecyclerView.Adapter<KelolaDokterAdapter.DokterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DokterViewHolder {
        val itemRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_klinik_kelola_dokter, parent, false)
        return DokterViewHolder(itemRow)
    }

    override fun getItemCount(): Int = listDokter.size

    override fun onBindViewHolder(holder: DokterViewHolder, position: Int) = holder.bind(
        listKeyDokter[position], listDokter[position], ubahListener, hapusListener
    )

    class DokterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtNamaDokter: TextView? = null
        private var txtDeskripsiDokter: TextView? = null
        private var imgUbahDokter: ImageView? = null
        private var imgHapusDokter: ImageView? = null

        init {
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterKelolaDokter)
            txtDeskripsiDokter = itemView.findViewById(R.id.txtDeskripsiDokterKelolaDokter)
            imgUbahDokter = itemView.findViewById(R.id.imgUbahDokter)
            imgHapusDokter = itemView.findViewById(R.id.imgHapusDokter)
        }

        fun bind(
            key: String,
            dokter: Dokter,
            ubahListener: (key: String, dokter: Dokter) -> Unit,
            hapusListener: (key: String) -> Unit
        ) {
            txtNamaDokter?.text = dokter.nama
            txtDeskripsiDokter?.text = dokter.deskripsi
            imgUbahDokter?.setOnClickListener {
                ubahListener(key, dokter)
            }
            imgHapusDokter?.setOnClickListener {
                hapusListener(key)
            }
        }
    }
}