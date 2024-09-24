package ru.grebe.moviescollection.films_list_screen.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.grebe.moviescollection.filmsList.R
import ru.grebe.moviescollection.filmsList.databinding.ItemFilmBinding
import ru.grebe.moviescollection.film_domain_api.model.FilmsModelDomain

class FilmCardRecyclerAdapter(
    private val filmListModel: List<FilmsModelDomain>,
    private val onCardClicked: (id: Int) -> Unit
) :
    RecyclerView.Adapter<FilmCardRecyclerAdapter.ImageNameViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ImageNameViewHolder {
        return ImageNameViewHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = filmListModel.size

    override fun onBindViewHolder(holder: ImageNameViewHolder, position: Int) {
        holder.binding.textViewMovieName.text =
            filmListModel[position].localizedName ?: filmListModel[position].name
        Glide
            .with(holder.binding.imageViewMovie.context)
            .load(filmListModel[position].imageUrl)
            .centerCrop()
            .placeholder(R.drawable.cat)
            .into(holder.binding.imageViewMovie)

        holder.binding.root.setOnClickListener {
            filmListModel[position].id?.let {
                onCardClicked(it)
            }
        }
    }

    class ImageNameViewHolder(
        val binding: ItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root)

}