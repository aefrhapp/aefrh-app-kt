package aefrh.es.aefrh.presentation.fiestas.list

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FiestaItemBinding
import aefrh.es.aefrh.diffUtils.DiffCallbackFiesta
import aefrh.es.aefrh.domain.Fiesta
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FiestasListAdapter: ListAdapter<Fiesta, FiestasListAdapter.ViewHolder>(DiffCallbackFiesta()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fiesta_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { fiesta ->
            with(holder) {
                itemView.tag = fiesta
                bind(createOnClickListener(fiesta.id), fiesta)
            }
        }
    }

    private fun createOnClickListener(epocaId: String): View.OnClickListener {
        return View.OnClickListener {
            val directions = FiestaListFragmentDirections.actionFragmentFiestaListToFragmentFiestaDetails(epocaId)
            it.findNavController().navigate(directions)
        }
    }

    class ViewHolder(private val binding: FiestaItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            listener: View.OnClickListener,
            value: Fiesta) {
            with(binding) {
                this.fiesta = value
                executePendingBindings()
            }
            binding.root.setOnClickListener(listener)
        }
    }

}