package aefrh.es.aefrh.utils.diffUtils

import aefrh.es.aefrh.domain.ContactoItem
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackContacto : DiffUtil.ItemCallback<ContactoItem>() {
    override fun areItemsTheSame(oldItem: ContactoItem, newItem: ContactoItem): Boolean {
        return oldItem.drawable == newItem.drawable
    }
    override fun areContentsTheSame(oldItem: ContactoItem, newItem: ContactoItem): Boolean {
        return oldItem == newItem
    }
}