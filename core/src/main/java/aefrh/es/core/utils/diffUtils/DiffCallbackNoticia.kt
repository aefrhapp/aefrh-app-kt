package aefrh.es.core.utils.diffUtils

import aefrh.es.core.domain.Noticia
import androidx.recyclerview.widget.DiffUtil

class DiffCallbackNoticia : DiffUtil.ItemCallback<Noticia>() {
    override fun areItemsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
        return oldItem == newItem
    }
}