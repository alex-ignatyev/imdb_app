package com.example.imdb_app.ui.movies.adapter.screen

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imdb_app.databinding.ItemMovieGroupTitleBinding
import com.example.imdb_app.databinding.ItemMoviesGroupBinding
import com.example.imdb_app.model.model.MovieDataModel
import com.example.imdb_app.util.recycler.AdapterItem
import com.example.imdb_app.util.recycler.BaseViewHolder
import com.example.imdb_app.util.recycler.viewBinding

const val MOVIES_LIST = 0
const val MOVIES_GROUP_TITLE = 1

class ScreenAdapter(
    private val onMovieClick: (String) -> Unit
) : ListAdapter<AdapterItem, BaseViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            MOVIES_LIST -> MovieGroupViewHolder(parent.viewBinding(ItemMoviesGroupBinding::inflate), onMovieClick)
            else -> MovieGroupTileViewHolder(parent.viewBinding(ItemMovieGroupTitleBinding::inflate))
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieDataModel -> MOVIES_LIST
            else -> MOVIES_GROUP_TITLE
        }
    }

    private object Differ : DiffUtil.ItemCallback<AdapterItem>() {
        override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem) = oldItem.id == newItem.id
    }
}
