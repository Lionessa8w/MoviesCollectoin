package ru.grebe.moviescollection.filmdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmDetails.R
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModelFactory
import ru.grebe.moviescollection.filmdetails.viewmodel.InfoFilmViewModel

private const val KEY_ID = "keyId"

class FilmsFragment : Fragment(), KoinComponent {

    private lateinit var image: ImageView
    private lateinit var localizedName: TextView
    private lateinit var year: TextView
    private lateinit var rating: TextView
    private lateinit var description: TextView
    private lateinit var viewModel: InfoFilmViewModel
    private lateinit var genre: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, FilmDetailsViewModelFactory(id = arguments?.getInt(KEY_ID) ?: 0)
        ).get(InfoFilmViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.film_info_scroll, container, false)
        image = view.findViewById(R.id.image)
        localizedName = view.findViewById(R.id.localized_name)
        year = view.findViewById(R.id.year)
        rating = view.findViewById(R.id.rating)
        description = view.findViewById(R.id.description)
        genre = view.findViewById(R.id.genre)

        viewModel.filmsModel.observe(viewLifecycleOwner) {
            localizedName.text = it.localizedName
            year.text = it.year.toString() + getString(R.string.card_year)
            rating.text = it.rating.toString() + getString(R.string.card_rating) + " "
            description.text = it.description
            genre.text = it.genres.first() + ", "
            Glide.with(this)
                .load(it.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.cat)
                .into(image)
        }
        return view
    }

    companion object {
        fun newInstance(id: Int): FilmsFragment {
            val args = Bundle()
            args.putInt(KEY_ID, id)
            val fragment = FilmsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}