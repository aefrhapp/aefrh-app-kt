package aefrh.es.aefrh.presentation.epocas

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.EpocaItemBinding
import aefrh.es.aefrh.domain.Epoca
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EpocasListAdapter: ListAdapter<Epoca, EpocasListAdapter.ViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.epoca_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { epoca ->
            with(holder) {
                itemView.tag = epoca
                bind(createOnClickListener(epoca.id), epoca)
            }
        }
    }

    private fun createOnClickListener(epocaId: String): View.OnClickListener {
        return View.OnClickListener {
//            val directions = FragmentEpocasDirections.actionFragmentEpocasToFragmentFiestaList(epocaId)
//            it.findNavController().navigate(directions)
        }
    }

    class ViewHolder(private val binding: EpocaItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, value: Epoca) {
            with(binding) {
                this.epoca = value
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }
    }
}

private class VideoDiffCallback : DiffUtil.ItemCallback<Epoca>() {
    override fun areItemsTheSame(oldItem: Epoca, newItem: Epoca): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Epoca, newItem: Epoca): Boolean {
        return oldItem == newItem
    }
}