package ru.grebe.moviescollection.filmlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescollectoin.filmsList.databinding.ItemGenreBinding

class GenresRecyclerAdapter(
    private val genres: List<String>,
    private val onItemClicked: (genre: String ) -> Unit
) :
    RecyclerView.Adapter<GenresRecyclerAdapter.GenresViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
        ): GenresViewHolder {
            return GenresViewHolder(ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context), parent,false))
        }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.binding.genresItem.text= genres[position]
        holder.binding.containerRoot.setOnClickListener {
            onItemClicked(genres[position])
        }
    }

    class GenresViewHolder(val binding: ItemGenreBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
