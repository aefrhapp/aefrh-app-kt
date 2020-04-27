package aefrh.es.aefrh.presentation.fiestas.information

import aefrh.es.aefrh.R
import aefrh.es.aefrh.databinding.FiestaContactoItemBinding
import aefrh.es.aefrh.diffUtils.DiffCallbackContacto
import aefrh.es.aefrh.domain.ContactoItem
import aefrh.es.aefrh.presentation.fiestas.FiestaViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FiestaContactoAdapter(private val viewModel: FiestaViewModel): ListAdapter<ContactoItem, FiestaContactoAdapter.ViewHolder>(DiffCallbackContacto()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.fiesta_contacto_item, parent, false
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

    class ViewHolder(private val binding: FiestaContactoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(value: ContactoItem, viewModel: FiestaViewModel) {
            binding.contacto = value
            binding.viewModel = viewModel
        }
    }

}