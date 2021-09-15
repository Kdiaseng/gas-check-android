package br.com.gascheck.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.gascheck.R
import br.com.gascheck.databinding.ItemDataGasBinding
import br.com.gascheck.ui.model.GasDataUi

class HistoricAdapter : ListAdapter<GasDataUi, HistoricAdapter.HistoricHolder>(object :

    DiffUtil.ItemCallback<GasDataUi>() {
    override fun areItemsTheSame(oldItem: GasDataUi, newItem: GasDataUi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: GasDataUi, newItem: GasDataUi): Boolean =
        oldItem.id == newItem.id

}) {

    inner class HistoricHolder(private val itemDataGasBinding: ItemDataGasBinding) :
        RecyclerView.ViewHolder(itemDataGasBinding.root) {

        fun bind(dataGas: GasDataUi) {

            itemDataGasBinding.apply {
                val context = this.root.context
                textItemNameGasStation.text = dataGas.name
                textItemTypeGas.text = dataGas.typeGas
                textItemValue.text = dataGas.value
                textItemDateRegister.text = context.getString(
                    R.string.item_value, dataGas.day, dataGas.month, dataGas.time
                )
                checkboxItemDislike.isChecked = dataGas.dislike ?: false
                checkboxItemLike.isChecked = dataGas.like ?: false
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoricAdapter.HistoricHolder =
        HistoricHolder(
            ItemDataGasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: HistoricAdapter.HistoricHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
