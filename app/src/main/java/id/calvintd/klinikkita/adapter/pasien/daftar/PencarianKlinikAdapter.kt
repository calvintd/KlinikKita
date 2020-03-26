package id.calvintd.klinikkita.adapter.pasien.daftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.database.Klinik

class PencarianKlinikAdapter(
    private val keyKlinikList: List<String>,
    private val listKlinik: List<Klinik>,
    private val klinikListener: (key: String, namaKlinik: String) -> Unit
) :
    RecyclerView.Adapter<PencarianKlinikAdapter.KlinikViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KlinikViewHolder {
        val itemRow = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pasien_pencarian_klinik, parent, false)
        return KlinikViewHolder(itemRow)
    }

    override fun getItemCount() = listKlinik.size

    override fun onBindViewHolder(holder: KlinikViewHolder, position: Int) =
        holder.bind(keyKlinikList[position], listKlinik[position], klinikListener)

    class KlinikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtNamaKlinik: TextView? = null
        private var txtAlamatKlinik: TextView? = null
        private var txtKotaKlinik: TextView? = null
        private var txtNomorHPKlinik: TextView? = null

        init {
            txtNamaKlinik = itemView.findViewById(R.id.txtNamaKlinikDaftarPasien)
            txtAlamatKlinik = itemView.findViewById(R.id.txtAlamatKlinikDaftarPasien)
            txtKotaKlinik = itemView.findViewById(R.id.txtKotaKlinikDaftarPasien)
            txtNomorHPKlinik = itemView.findViewById(R.id.txtNomorHPKlinikDaftarPasien)
        }

        fun bind(key: String, klinik: Klinik, klinikListener: (String, String) -> Unit) {
            txtNamaKlinik?.text = klinik.namaKlinik
            txtAlamatKlinik?.text = klinik.alamat
            txtKotaKlinik?.text = klinik.kota
            txtNomorHPKlinik?.text = klinik.nomorHP
            itemView.setOnClickListener {
                klinikListener(key, klinik.namaKlinik)
            }
        }
    }
}