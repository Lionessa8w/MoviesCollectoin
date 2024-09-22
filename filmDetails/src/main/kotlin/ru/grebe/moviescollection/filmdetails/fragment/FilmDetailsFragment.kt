package ru.grebe.moviescollection.filmdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmDetails.R
import com.example.moviescollectoin.filmDetails.databinding.FilmInfoScrollBinding
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModelFactory
import ru.grebe.moviescollection.filmdetails.viewmodel.InfoFilmViewModel

private const val KEY_ID = "keyId"

class FilmDetailsFragment : Fragment(), KoinComponent {

    private var binding: FilmInfoScrollBinding? = null
    private var viewModel: InfoFilmViewModel? = null


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
    ): View {

        val binding = FilmInfoScrollBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val viewModel = viewModel ?: return

        viewModel.filmsModel.observe(viewLifecycleOwner) {

            binding.localizedName.text = it.localizedName
            binding.year.text = it.year.toString() +" "+ getString(R.string.card_year)
            val rating= (it.rating!!*10.0).toInt()/10.0
            binding.rating.text = rating.toString() +" "+getString(R.string.card_rating)
            binding.description.text = it.description
            binding.genre.text = it.genres.first() + ", "
            Glide.with(this)
                .load(it.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.cat)
                .into(binding.image)
        }
    }

    companion object {
        fun newInstance(id: Int): FilmDetailsFragment {
            val args = Bundle()
            args.putInt(KEY_ID, id)
            val fragment = FilmDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}