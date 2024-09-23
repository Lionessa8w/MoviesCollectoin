package ru.grebe.moviescollection.filmlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmsList.R
import com.example.moviescollectoin.filmsList.databinding.ItemFilmBinding
import ru.grebe.moviescollection.filmdomain.model.FilmsModelDomain

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
            filmListModel[position].localizedName
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