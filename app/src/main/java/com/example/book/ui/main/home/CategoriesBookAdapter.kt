package com.example.book.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.book.R
import com.example.book.api.model.response.categories.CategoriesResponseItem
import com.example.book.databinding.ItemHomeCategoryBinding


class CategoriesBookAdapter(
    private var data: List<CategoriesResponseItem>,
    private val onClickListener: (CategoriesResponseItem) -> Unit
) : RecyclerView.Adapter<CategoriesBookAdapter.ViewHolder>() {

    var selectedPosition: Int = RecyclerView.NO_POSITION

    inner class ViewHolder(private val binding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoriesResponseItem) {

            with(binding){
                //imageCategory.load(item.image)
                tvNameCategory.text = buildString {
                    append(item.title)
                }

                if (adapterPosition == selectedPosition) {
                    imageCategory.setBackgroundResource(R.drawable.bookc1)
                } else {
                    imageCategory.setBackgroundResource(R.drawable.bookc)
                }
            }

            binding.root.setOnClickListener {
                onClickListener(item)
                if (selectedPosition == adapterPosition) {
                    // Desmarcar o item selecionado
                    selectedPosition = RecyclerView.NO_POSITION
                } else {
                    // Marcar o novo item selecionado
                    selectedPosition = adapterPosition
                }
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesBookAdapter.ViewHolder {
        val binding =
            ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesBookAdapter.ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<CategoriesResponseItem>) {
        data = newData
        notifyDataSetChanged()
    }

}