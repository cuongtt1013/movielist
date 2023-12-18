package com.example.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.movielist.R
import com.example.movielist.databinding.FilmCellBinding
import com.example.movielist.model.Movie

class SearchAdapter :
    PagingDataAdapter<Movie, BaseViewHolder<*>>(ItemDiffCallback.diffCallback) {
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (holder) {
            is SearchViewHolder -> holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FilmCellBinding>(
            layoutInflater,
            R.layout.film_cell,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    class SearchViewHolder(
        private val binding: FilmCellBinding
    ) :
        BaseViewHolder<Movie?>(binding.root) {

        override fun bind(data: Movie?) {
            if (data == null) return
            binding.item = data
        }
    }

    object ItemDiffCallback {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean =
                oldItem.title == newItem.title
        }
    }
}

@BindingAdapter("imageUrlLogo")
fun setImageUrlLogo(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .fallback(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .into(imageView)
}