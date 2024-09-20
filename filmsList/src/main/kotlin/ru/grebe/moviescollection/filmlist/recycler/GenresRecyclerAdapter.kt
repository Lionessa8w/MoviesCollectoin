package ru.grebe.moviescollection.filmlist.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.moviescollectoin.filmsList.R

class GenresRecyclerAdapter(
    private val genres: List<String>,
    private val onItemClicked: (genre: String ) -> Unit
) :
    RecyclerView.Adapter<GenresRecyclerAdapter.GenresViewHolder>() {

    class GenresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genresText: TextView = itemView.findViewById(R.id.genres_item)
        val containerRoot: ConstraintLayout = itemView.findViewById(R.id.container_root)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false)
        return GenresViewHolder(itemView)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        holder.genresText.text = genres[position]
        holder.containerRoot.setOnClickListener {
            onItemClicked(genres[position])

            Log.d("checkResult", "onBindViewHolder: клик работает")
        }
    }
}