package n71.inc.calculatorimt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import n71.inc.calculatorimt.R
import n71.inc.calculatorimt.databinding.ListHistoryBinding
import n71.inc.calculatorimt.helper.IClickModel
import n71.inc.calculatorimt.model.Model

class AdapterFavorite(
    private val dataList : ArrayList<Model>,
    private val listener : IClickModel) :
    RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {

    private lateinit var binding : ListHistoryBinding

    inner class ViewHolder(binding: ListHistoryBinding) : RecyclerView.ViewHolder(binding.root){

        var binding: ListHistoryBinding

        init {
            this.binding = binding
            binding.listener = listener
        }

        fun bind(model: Model?) {
            binding.model = model
            binding.executePendingBindings()

            binding.txtResult.setTextColor(model!!.colors)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.list_history, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}