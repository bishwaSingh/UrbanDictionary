package awesome.shizzle.urbandictionary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import awesome.shizzle.urbandictionary.databinding.ListItemDefinitionBinding
import awesome.shizzle.urbandictionary.model.Response

class DefinitionsListAdapter :
    ListAdapter<Response.UrbanDefinition, DefinitionsListAdapter.DefinitionViewHolder>(DefinitionDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        return DefinitionViewHolder(
            ListItemDefinitionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DefinitionViewHolder(private val binding: ListItemDefinitionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(definition: Response.UrbanDefinition) {
            binding.itemDefinition = definition
        }

    }

    object DefinitionDiffUtil : DiffUtil.ItemCallback<Response.UrbanDefinition>() {
        override fun areItemsTheSame(oldItem: Response.UrbanDefinition, newItem: Response.UrbanDefinition) =
            oldItem.defid == newItem.defid

        override fun areContentsTheSame(oldItem: Response.UrbanDefinition, newItem: Response.UrbanDefinition) =
            oldItem == newItem
    }
}