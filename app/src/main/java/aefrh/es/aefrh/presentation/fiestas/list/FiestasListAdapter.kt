package aefrh.es.aefrh.presentation.fiestas.list

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ItemFiestaBinding
import aefrh.es.aefrh.diffUtils.DiffCallbackFiesta
import aefrh.es.aefrh.domain.Fiesta
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FiestasListAdapter(private val viewModel: FiestaViewModel): ListAdapter<Fiesta, FiestasListAdapter.ViewHolder>(DiffCallbackFiesta()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_fiesta, parent, false
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

    class ViewHolder(private val binding: ItemFiestaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Fiesta, viewModel: FiestaViewModel) {
            binding.fiesta = value
            binding.viewModel = viewModel
        }
    }

}