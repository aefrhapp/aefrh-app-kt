package aefrh.es.aefrh.diffUtils

import aefrh.es.aefrh.domain.Fiesta
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackFiesta : DiffUtil.ItemCallback<Fiesta>() {
    override fun areItemsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
        return oldItem == newItem
    }
}