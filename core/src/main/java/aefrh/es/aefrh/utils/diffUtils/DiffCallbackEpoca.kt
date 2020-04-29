package aefrh.es.aefrh.utils.diffUtils

import aefrh.es.aefrh.domain.Epoca
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackEpoca : DiffUtil.ItemCallback<Epoca>() {
    override fun areItemsTheSame(oldItem: Epoca, newItem: Epoca): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Epoca, newItem: Epoca): Boolean {
        return oldItem == newItem
    }
}