package id.calvintd.klinikkita.adapter.klinik.pemeriksaan

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.itemmodel.database.Pendaftaran

class KelolaPendaftaranAdapter(
    private val listKeyPendaftaran: List<String>,
    private val listPendaftaran: List<Pendaftaran>,
    private val hadirListener: (String, Pendaftaran) -> Unit,
    private val tidakHadirListener: (String) -> Unit
) : RecyclerView.Adapter<KelolaPendaftaranAdapter.PendaftaranViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendaftaranViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = listPendaftaran.size

    override fun onBindViewHolder(holder: PendaftaranViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class PendaftaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}