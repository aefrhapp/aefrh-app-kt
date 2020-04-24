package aefrh.es.aefrh.diffUtils

import aefrh.es.aefrh.domain.InformacionItem
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackInformacion : DiffUtil.ItemCallback<InformacionItem>() {
    override fun areItemsTheSame(oldItem: InformacionItem, newItem: InformacionItem): Boolean {
        return oldItem.key == newItem.key
    }
    override fun areContentsTheSame(oldItem: InformacionItem, newItem: InformacionItem): Boolean {
        return oldItem == newItem
    }
}