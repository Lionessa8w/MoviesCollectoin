package fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmDetails.R
import kotlinx.coroutines.launch
import viewModel.InfoFilmViewModel
import viewModel.InfoFilmViewModelFactory

private const val KEY_ID = "keyId"

class FilmsFragment : Fragment() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var localizedName: TextView
    private lateinit var year: TextView
    private lateinit var rating: TextView
    private lateinit var description: TextView
    private var viewModel: InfoFilmViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(
            this,
            InfoFilmViewModelFactory(id = arguments?.getInt(KEY_ID) ?: 0)
        ).get(
            InfoFilmViewModel::class.java
        )
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.film_info_scroll, container, false)
        image = view.findViewById(R.id.image)
        name = view.findViewById(R.id.name)
        localizedName = view.findViewById(R.id.localized_name)
        year = view.findViewById(R.id.year)
        rating = view.findViewById(R.id.rating)
        description = view.findViewById(R.id.description)

        viewModel?.filmsModel?.observe(viewLifecycleOwner) {

            name.text = getString(R.string.card_name)+it.name
            localizedName.text = it.localizedName
            year.text = getString(R.string.card_year)+it.year.toString()
            rating.text = getString(R.string.card_rating)+it.rating.toString()
            description.text = it.description
            Glide
                .with(this)
                .load(it.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.cat)
                .into(image)

        }


        lifecycleScope.launch {
            Log.d("checkResult", ":FilmsFragment is work")
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