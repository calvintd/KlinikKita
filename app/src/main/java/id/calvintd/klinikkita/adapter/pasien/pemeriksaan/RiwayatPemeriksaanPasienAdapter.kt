package id.calvintd.klinikkita.adapter.pasien.pemeriksaan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.PemeriksaanInternal
import id.calvintd.klinikkita.util.KonversiWaktu

class RiwayatPemeriksaanPasienAdapter(private val listPemeriksaanPasien: List<PemeriksaanInternal>) :
    RecyclerView.Adapter<RiwayatPemeriksaanPasienAdapter.PemeriksaanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemeriksaanViewHolder {
        val itemRow = LayoutInflater.from(parent.context).inflate(R.layout.item_pasien_riwayat_pemeriksaan, parent, false)
        return PemeriksaanViewHolder(itemRow)
    }

    override fun getItemCount() = listPemeriksaanPasien.size

    override fun onBindViewHolder(holder: PemeriksaanViewHolder, position: Int) = holder.bind(listPemeriksaanPasien[position])

    class PemeriksaanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtIsiWaktu: TextView? = null
        private var txtNamaKlinik: TextView? = null
        private var txtNamaDokter: TextView? = null
        private var txtIsiKeluhan: TextView? = null
        private var txtIsiDiagnosis: TextView? = null
        private var txtIsiPengobatan: TextView? = null

        init {
            txtIsiWaktu = itemView.findViewById(R.id.txtIsiWaktuRiwayatPemeriksaanPasien)
            txtNamaKlinik = itemView.findViewById(R.id.txtNamaKlinikRiwayatPemeriksaanPasien)
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterRiwayatPemeriksaanPasien)
            txtIsiKeluhan = itemView.findViewById(R.id.txtIsiKeluhanRiwayatPemeriksaanPasien)
            txtIsiDiagnosis = itemView.findViewById(R.id.txtIsiDiagnosisRiwayatPemeriksaanPasien)
            txtIsiPengobatan = itemView.findViewById(R.id.txtIsiPengobatanRiwayatPemeriksaanPasien)
        }

        fun bind(pemeriksaan: PemeriksaanInternal) {
            txtIsiWaktu?.text = pemeriksaan.waktu?.let { KonversiWaktu.konversi(it) }
            txtNamaKlinik?.text = pemeriksaan.namaKlinik
            txtNamaDokter?.text = pemeriksaan.namaDokter
            txtIsiKeluhan?.text = pemeriksaan.keluhan
            txtIsiDiagnosis?.text = pemeriksaan.diagnosis
            txtIsiPengobatan?.text = pemeriksaan.pengobatan
        }
    }
}