package aefrh.es.core.utils.diffUtils

import aefrh.es.core.domain.ContactoItem
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackContacto : DiffUtil.ItemCallback<ContactoItem>() {
    override fun areItemsTheSame(oldItem: ContactoItem, newItem: ContactoItem): Boolean {
        return oldItem.drawable == newItem.drawable
    }
    override fun areContentsTheSame(oldItem: ContactoItem, newItem: ContactoItem): Boolean {
        return oldItem == newItem
    }
}