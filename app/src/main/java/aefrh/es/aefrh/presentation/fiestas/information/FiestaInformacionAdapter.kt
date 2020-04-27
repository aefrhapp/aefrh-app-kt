package aefrh.es.aefrh.presentation.fiestas.information

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FiestaInformacionItemBinding
import aefrh.es.aefrh.diffUtils.DiffCallbackInformacion
import aefrh.es.aefrh.domain.InformacionItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FiestaInformacionAdapter: ListAdapter<InformacionItem, FiestaInformacionAdapter.ViewHolder>(DiffCallbackInformacion()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fiesta_informacion_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(it)
            }
        }
    }

    class ViewHolder(private val binding: FiestaInformacionItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: InformacionItem) {
            binding.informacion = value
        }
    }

}