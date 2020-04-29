package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.ItemEpocaBinding
import aefrh.es.aefrh.domain.Epoca
import aefrh.es.aefrh.utils.diffUtils.DiffCallbackEpoca
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EpocasListAdapter(private val viewModel: EpocasViewModel): ListAdapter<Epoca, EpocasListAdapter.ViewHolder>(DiffCallbackEpoca()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_epoca, parent, false
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

    class ViewHolder(private val binding: ItemEpocaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: Epoca, viewModel: EpocasViewModel) {
            binding.epoca = value
            binding.viewModel = viewModel
        }
    }

}