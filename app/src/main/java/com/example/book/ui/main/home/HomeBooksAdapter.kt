package com.example.book.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.book.api.model.response.book.DataBook
import com.example.book.databinding.ItemHomeRecommendedBinding
import com.google.android.gms.common.api.GoogleApi
import java.text.NumberFormat
import java.util.Locale


class HomeBooksAdapter(
    private var data: List<DataBook?>,
    private val onClickListener: (DataBook?) -> Unit,
    private val onDeleteBookClick: (DataBook) -> Unit
) : RecyclerView.Adapter<HomeBooksAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemHomeRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataBook) {

            with(binding){

                tvBook.text = item.title
                tvAuthor.text = item.author

                imageBook.load(item.imageUrl)
                btnTrash.visibility= View.GONE
            }

            binding.root.setOnClickListener {
                onClickListener(item)
            }


        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeBooksAdapter.ViewHolder {
        val binding =
            ItemHomeRecommendedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBooksAdapter.ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItemViewType(position: Int): Int = position

    fun updateData(newData: List<DataBook?>) {
        data = newData
        notifyDataSetChanged()
    }

}