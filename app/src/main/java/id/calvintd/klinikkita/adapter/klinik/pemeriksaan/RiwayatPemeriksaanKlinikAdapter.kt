package id.calvintd.klinikkita.adapter.klinik.pemeriksaan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.calvintd.klinikkita.R
import id.calvintd.klinikkita.itemmodel.internal.PemeriksaanInternal
import id.calvintd.klinikkita.util.DateTimeConverter

class RiwayatPemeriksaanKlinikAdapter(private val listPemeriksaanKlinik: List<PemeriksaanInternal>) :
    RecyclerView.Adapter<RiwayatPemeriksaanKlinikAdapter.PemeriksaanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemeriksaanViewHolder {
        val itemRow = LayoutInflater.from(parent.context).inflate(R.layout.item_klinik_riwayat_pemeriksaan, parent, false)
        return PemeriksaanViewHolder(itemRow)
    }

    override fun getItemCount() = listPemeriksaanKlinik.size

    override fun onBindViewHolder(holder: PemeriksaanViewHolder, position: Int) = holder.bind(listPemeriksaanKlinik[position])

    class PemeriksaanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var txtIsiWaktu: TextView? = null
        private var txtNamaPasien: TextView? = null
        private var txtNamaDokter: TextView? = null
        private var txtIsiKeluhan: TextView? = null
        private var txtIsiDiagnosis: TextView? = null
        private var txtIsiPengobatan: TextView? = null

        init {
            txtIsiWaktu = itemView.findViewById(R.id.txtIsiWaktuRiwayatPemeriksaanKlinik)
            txtNamaPasien = itemView.findViewById(R.id.txtNamaPasienRiwayatPemeriksaanKlinik)
            txtNamaDokter = itemView.findViewById(R.id.txtNamaDokterRiwayatPemeriksaanKlinik)
            txtIsiKeluhan = itemView.findViewById(R.id.txtIsiKeluhanRiwayatPemeriksaanKlinik)
            txtIsiDiagnosis = itemView.findViewById(R.id.txtIsiDiagnosisRiwayatPemeriksaanKlinik)
            txtIsiPengobatan = itemView.findViewById(R.id.txtIsiPengobatanRiwayatPemeriksaanKlinik)
        }

        fun bind(pemeriksaan: PemeriksaanInternal) {
            txtIsiWaktu?.text = pemeriksaan.waktu?.let { DateTimeConverter.konversi(it) }
            txtNamaPasien?.text = pemeriksaan.namaPasien
            txtNamaDokter?.text = pemeriksaan.namaDokter
            txtIsiKeluhan?.text = pemeriksaan.keluhan
            txtIsiDiagnosis?.text = pemeriksaan.diagnosis
            txtIsiPengobatan?.text = pemeriksaan.pengobatan
        }
    }
}