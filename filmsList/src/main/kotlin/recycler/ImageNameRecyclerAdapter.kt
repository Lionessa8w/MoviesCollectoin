package recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescollectoin.filmsList.R

class ImageNameRecyclerAdapter(
    private val filmListModel: List<Unit>, // todo change this
    private val onCardClicked: (id: Int) -> Unit,
    private val onLikeClicked: (id: Int) -> Unit,
    private val onIgnoreClicked: (id: Int) -> Unit,

    ) :
    RecyclerView.Adapter<ImageNameRecyclerAdapter.ImageNameViewHolder>() {

    class ImageNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filmImage: ImageView = itemView.findViewById(R.id.drawable_movie_item)
        val filmLocalized: TextView = itemView.findViewById(R.id.localized_name_item)
        val imageFilmRoot: CardView = itemView.findViewById(R.id.film_image_root)
        val imageLike: ImageView = itemView.findViewById(R.id.imageLike)
        val imageIgnore: ImageView = itemView.findViewById(R.id.imageView_ignore)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageNameViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return ImageNameViewHolder(itemView)

    }

    override fun getItemCount(): Int = filmListModel.size

    override fun onBindViewHolder(holder: ImageNameViewHolder, position: Int) {
        renderLike(holder, position)
//        holder.filmLocalized.text = filmListModel[position].localizedName
//        Glide
//            .with(holder.filmImage.context)
//            .load(filmListModel[position].imageUrl)
//            .centerCrop()
//            .placeholder(R.drawable.cat)
//            .into(holder.filmImage)
//        holder.imageFilmRoot.setOnClickListener {
//            filmListModel[position].id?.let {
//                onCardClicked(it)
//            }
//        }
//        holder.imageLike.setOnClickListener {
//            filmListModel[position].id?.let { id ->
//                onLikeClicked(id)
//                Log.d("likeClick", "работает")
//                checkLike(holder, position)
//                Log.d("checkLike", "работает ")
//
//            }
//        }
//        holder.imageIgnore.setOnClickListener {
//            filmListModel[position].id?.let { id ->
//                onIgnoreClicked(id)
//                checkIgnore(holder, position)
//            }
//        }


    }

    fun checkLike(holder: ImageNameViewHolder, position: Int) {
//        filmListModel[position].isLiked = !filmListModel[position].isLiked
        renderLike(holder, position)
    }

    fun checkIgnore(holder: ImageNameViewHolder, position: Int) {
//        filmListModel[position].isIgnore = !filmListModel[position].isIgnore
        renderIgnore(holder, position)
    }

    private fun renderLike(holder: ImageNameViewHolder, position: Int) {
//        if (filmListModel[position].isLiked) {
//            holder.imageLike.setImageDrawable(
//                AppCompatResources.getDrawable(
//                    holder.imageLike.context,
//                    R.drawable.baseline_favorite_24
//                )
//            )
//        } else {
//            holder.imageLike.setImageDrawable(
//                AppCompatResources.getDrawable(
//                    holder.imageLike.context,
//                    R.drawable.baseline_0
//                )
//            )
//        }
    }

    private fun renderIgnore(holder: ImageNameViewHolder, position: Int) {
//        if (filmListModel[position].isIgnore) {
//            holder.imageIgnore.setImageDrawable(
//                AppCompatResources.getDrawable(
//                    holder.imageIgnore.context,
//                    R.drawable.baseline_ignore_red
//                )
//            )
//        } else {
//            holder.imageIgnore.setImageDrawable(
//                AppCompatResources.getDrawable(
//                    holder.imageIgnore.context,
//                    R.drawable.baseline_ignore_0
//                )
//            )
//        }
    }
}