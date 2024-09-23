package ru.grebe.moviescollection.filmdetails.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmDetails.R
import com.example.moviescollectoin.filmDetails.databinding.FilmInfoScrollBinding
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModelFactory
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModel
import toolbar.ToolbarHolder

const val ARGUMENT_ID_KEY = "argument_id_key"

class FilmDetailsFragment : Fragment(), KoinComponent {

    private var binding: FilmInfoScrollBinding? = null
    private var viewModel: FilmDetailsViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, FilmDetailsViewModelFactory(id = arguments?.getInt(ARGUMENT_ID_KEY) ?: 0)
        ).get(FilmDetailsViewModel::class.java)
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

        Glide.with(this)
//            .load(R.drawable.)
//            .into()

        lifecycleScope.launch {
            viewModel.filmsModel.collect {

                binding.localizedName.text = it.localizedName
                binding.year.text = it.year.toString() + " " + getString(R.string.card_year)
                val rating = (it.rating!! * 10.0).toInt() / 10.0
                binding.rating.text = rating.toString() + " " + getString(R.string.card_rating)
                binding.description.text = it.description
                binding.genre.text = it.genres.first() + ", "

                Glide.with(this@FilmDetailsFragment)
                    .load(it.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.cat)
                    .into(binding.image)

                (activity as? ToolbarHolder)?.changeToolbarTitle(it.name ?: "")
            }
        }

    }
}