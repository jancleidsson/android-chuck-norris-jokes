package com.jss.chucknorrisjokes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jss.chucknorrisjokes.R
import com.jss.chucknorrisjokes.core.entity.Category
import com.jss.chucknorrisjokes.databinding.FragmentJokesCategoryListItemBinding

class JokesCategoryAdapter(private val onCategoryClicked: (Category) -> Unit) : RecyclerView.Adapter<JokesCategoryAdapter.ViewHolder>() {

    private var categories = arrayListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_jokes_category_list_item, parent, false)
        return ViewHolder(view) { onCategoryClicked(categories[it]) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        categories[position].let { holder.bindTo(it) }
        holder.itemView.setOnClickListener { onCategoryClicked(categories[position]) }
    }

    override fun getItemCount(): Int = categories.size

    fun updateCategories(categoriesData: List<Category>) {
        categories.clear()
        categories.addAll(categoriesData)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, onItemClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentJokesCategoryListItemBinding.bind(view)

        init {
            itemView.setOnClickListener {
                onItemClicked(absoluteAdapterPosition)
            }
        }

        fun bindTo(category: Category) {
            binding.categoryName.text = category.name
        }
    }
}