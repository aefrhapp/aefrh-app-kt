package aefrh.es.aefrh.presentation.fiestas.information

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ItemFiestaInformacionBinding
import aefrh.es.core.domain.InformacionItem
import aefrh.es.core.utils.diffUtils.DiffCallbackInformacion
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
                R.layout.item_fiesta_informacion, parent, false
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

    class ViewHolder(private val binding: ItemFiestaInformacionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: InformacionItem) {
            binding.informacion = value
        }
    }

}