package ru.grebe.moviescollection.film_details_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import ru.grebe.moviescollection.filmDetails.R
import ru.grebe.moviescollection.filmDetails.databinding.FilmInfoScrollBinding
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.film_details_screen.viewmodel.FilmDetailsViewModelFactory
import ru.grebe.moviescollection.film_details_screen.viewmodel.FilmDetailsViewModel
import ru.grebe.moviescollection.film_details_screen.viewmodel.FilmDetailsViewModelState
import ru.grebe.moviescollection.snackbar_holder.SnackBarHolder
import ru.grebe.moviescollection.toolbar_holder.ToolbarHolder

const val ARGUMENT_ID_KEY = "argument_id_key"

class FilmDetailsFragment : Fragment(), KoinComponent {

    private var binding: FilmInfoScrollBinding? = null
    private var viewModel: FilmDetailsViewModel? = null
    private var onErrorClick: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, FilmDetailsViewModelFactory(id = arguments?.getInt(ARGUMENT_ID_KEY) ?: 0)
        )[FilmDetailsViewModel::class.java]
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

        onErrorClick = { viewModel.getFilm() }

        Glide.with(this)
            .load(R.drawable.loading_gif)
            .centerCrop()
            .placeholder(R.drawable.cat)
            .into(binding.imageViewLoading)

        lifecycleScope.launch {
            viewModel.filmsModel.collect { state ->
                when (state) {
                    is FilmDetailsViewModelState.Error -> {
                        showOrHideLoadingContainer(false)
                        showErrorLoadingSnackBar()
                    }

                    FilmDetailsViewModelState.Loading -> {
                        showOrHideLoadingContainer(true)
                    }

                    is FilmDetailsViewModelState.Success -> {
                        showOrHideLoadingContainer(false)
                        binding.scrollViewRoot.isVisible = true
                        binding.localizedName.text = state.filmModelDetails.localizedName.orEmpty()
                        if (state.filmModelDetails.rating == null) {
                            binding.ratingValue.isVisible = false
                            binding.ratingTextView.isVisible = false
                        } else {
                            val ratingText= state.filmModelDetails.rating.toString()
                            val ratingValue=((ratingText.toDouble()*10.0).toInt()/10.0).toString()
                            binding.ratingValue.text = ratingValue
                        }

                        binding.description.text = state.filmModelDetails.description.orEmpty()

                        if (state.filmModelDetails.genres.isEmpty()) {
                            binding.genre.text =
                                "${state.filmModelDetails.year} ${getString(R.string.card_year)}"
                        } else {
                            binding.genre.text =
                                state.filmModelDetails.genres.joinToString(separator = ", ") { it } +
                                        ", ${state.filmModelDetails.year} ${getString(R.string.card_year)}"
                        }

                        Glide.with(this@FilmDetailsFragment)
                            .load(state.filmModelDetails.imageUrl)
                            .centerCrop()
                            .placeholder(R.drawable.cat)
                            .into(binding.image)

                        (activity as? ToolbarHolder)?.changeToolbarTitle(
                            state.filmModelDetails.name ?: ""
                        )
                    }
                }
            }
        }

    }

    private fun showOrHideLoadingContainer(isShow: Boolean) {
        val binding = binding ?: return
        binding.containerLoading.isVisible = isShow
    }

    private fun showErrorLoadingSnackBar() {
        (activity as? SnackBarHolder)?.showErrorSnackBar(onErrorClick ?: return)
    }

    override fun onDestroyView() {
        onErrorClick = null
        super.onDestroyView()
    }
}