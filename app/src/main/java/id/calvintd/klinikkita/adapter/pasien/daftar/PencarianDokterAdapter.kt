package id.calvintd.klinikkita.adapter.pasien.daftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Dokter

class PencarianDokterAdapter(
    private val keyDokterList: List<String>,
    private val listDokter: List<Dokter>,
    private val dokterListener: (key: String, namaDokter: String) -> Unit
) :
    RecyclerView.Adapter<PencarianDokterAdapter.DokterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DokterViewHolder {
        val itemRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pasien_pencarian_dokter, parent, false)
        return DokterViewHolder(itemRow)
    }

    override fun getItemCount() = listDokter.size

    override fun onBindViewHolder(holder: DokterViewHolder, position: Int) =
        holder.bind(keyDokterList[position], listDokter[position], dokterListener)

    class DokterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtNamaDokter: TextView? = null
        private var txtDeskripsiDokter: TextView? = null

        init {
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterDaftarPasien)
            txtDeskripsiDokter = itemView.findViewById(R.id.txtDeskripsiDokterDaftarPasien)
        }

        fun bind(key: String, dokter: Dokter, dokterListener: (String, String) -> Unit) {
            txtNamaDokter?.text = dokter.nama
            txtDeskripsiDokter?.text = dokter.deskripsi
            itemView.setOnClickListener {
                dokterListener(key, dokter.nama)
            }
        }
    }
}