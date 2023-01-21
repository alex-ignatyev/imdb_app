package com.sideki.imdb_app.ui.movies_list.adapter.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sideki.imdb_app.databinding.ItemMovieGroupTitleBinding
import com.sideki.imdb_app.databinding.ItemMoviesGroupBinding
import com.sideki.imdb_app.model.model.MovieDataModel
import com.sideki.imdb_app.util.recycler.AdapterItem
import com.sideki.imdb_app.util.recycler.BaseViewHolder

const val MOVIES_LIST = 0
const val MOVIES_GROUP_TITLE = 1

class ScreenAdapter(
    private val onMovieClick: (String) -> Unit
) : ListAdapter<AdapterItem, BaseViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == MOVIES_LIST) {
            val binding = ItemMoviesGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MovieGroupViewHolder(binding, onMovieClick)
        } else {
            val binding = ItemMovieGroupTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MovieGroupTileViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is MovieDataModel -> MOVIES_LIST
            else -> MOVIES_GROUP_TITLE
        }
    }

    private object Differ : DiffUtil.ItemCallback<AdapterItem>() {
        override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem) = oldItem.id == newItem.id
    }
}
