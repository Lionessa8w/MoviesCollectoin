package recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmsList.R
import model.FilmsModel

class ImageNameRecyclerAdapter(
    private val filmListModel: List<FilmsModel>,
    private val onCardClicked: (id: Int) -> Unit
) :
    RecyclerView.Adapter<ImageNameRecyclerAdapter.ImageNameViewHolder>() {

    class ImageNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmImage: ImageView = itemView.findViewById(R.id.drawable_movie_item)
        val filmLocalized: TextView = itemView.findViewById(R.id.localized_name_item)
        val imageFilmRoot: CardView = itemView.findViewById(R.id.film_image_root)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageNameViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ImageNameViewHolder(itemView)

    }

    override fun getItemCount(): Int = filmListModel.size

    override fun onBindViewHolder(holder: ImageNameViewHolder, position: Int) {
        holder.filmLocalized.text = filmListModel[position].localizedName
        Glide
            .with(holder.filmImage.context)
            .load(filmListModel[position].imageUrl)
            .centerCrop()
            .placeholder(R.drawable.cat)
            .into(holder.filmImage)
        holder.imageFilmRoot.setOnClickListener {
            filmListModel[position].id?.let {
                onCardClicked(it)
            }
        }


    }

}