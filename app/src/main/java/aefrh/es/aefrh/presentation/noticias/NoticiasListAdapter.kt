package aefrh.es.aefrh.presentation.noticias

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ItemNoticiaBinding
import aefrh.es.core.domain.Noticia
import aefrh.es.core.utils.diffUtils.DiffCallbackNoticia
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoticiasListAdapter(private val viewModel: NoticiasViewModel): ListAdapter<Noticia, NoticiasListAdapter.ViewHolder>(DiffCallbackNoticia()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_noticia, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(it, viewModel)
            }
        }
    }

    class ViewHolder(private val binding: ItemNoticiaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Noticia, viewModel: NoticiasViewModel) {
            binding.noticia = value
            binding.viewModel = viewModel
        }
    }

}