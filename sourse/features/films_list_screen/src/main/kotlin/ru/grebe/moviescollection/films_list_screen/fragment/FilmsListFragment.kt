package ru.grebe.moviescollection.films_list_screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.filmsList.R
import ru.grebe.moviescollection.filmsList.databinding.FragmentFilmListBinding
import ru.grebe.moviescollection.films_list_screen.recycler.FilmCardRecyclerAdapter
import ru.grebe.moviescollection.films_list_screen.recycler.GenresRecyclerAdapter
import ru.grebe.moviescollection.films_list_screen.viewmodel.FilmListViewModelState
import ru.grebe.moviescollection.films_list_screen.viewmodel.FilmsListViewModel
import ru.grebe.moviescollection.navigation.NavigationAction
import ru.grebe.moviescollection.navigation.NavigationHolder
import ru.grebe.moviescollection.snackbar_holder.SnackBarHolder

class FilmsListFragment : Fragment(), KoinComponent {

    private val viewModel: FilmsListViewModel by inject<FilmsListViewModel>()
    private var binding: FragmentFilmListBinding? = null
    private var onErrorClick: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmListBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        binding.filmsListRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.genresListRecycler.layoutManager = LinearLayoutManager(context)

        onErrorClick = { viewModel.retryGetFilms() }

        Glide.with(this)
            .load(R.drawable.loading_gif)
            .centerCrop()
            .placeholder(R.drawable.cat)
            .into(binding.imageViewLoading)

        binding.textViewGenre.setOnClickListener {
            val isVisible = binding.genresListRecycler.isVisible
            binding.genresListRecycler.isVisible = !isVisible
            if (isVisible) {
                binding.imageViewArrow.animate().rotation(180f).setDuration(100).start()
            } else {
                binding.imageViewArrow.animate().rotation(0f).setDuration(100).start()
            }
        }

        lifecycleScope.launch {
            viewModel.listFilmsState.collect { state ->
                when (state) {
                    is FilmListViewModelState.Error -> {
                        showOrHideLoadingContainer(false)
                        showErrorLoadingSnackBar()
                    }

                    FilmListViewModelState.Loading -> {
                        showOrHideLoadingContainer(true)
                    }

                    is FilmListViewModelState.Success -> {
                        binding.containerContent.isVisible = true
                        showOrHideLoadingContainer(false)
                        binding.filmsListRecycler.adapter =
                            FilmCardRecyclerAdapter(
                                filmListModel = state.filmsList,
                                onCardClicked = { id ->
                                    openFilmDetailsFragment(id)
                                }
                            )

                        binding.genresListRecycler.adapter =
                            GenresRecyclerAdapter(
                                genres = state.genresList,
                                selectedGenre = state.selectedGenre
                            ) { genre ->
                                viewModel.setCurrentGenre(genre)
                            }
                    }
                }
            }
        }
    }

    private fun openFilmDetailsFragment(id: Int) {
        (activity as? NavigationHolder)?.doNavigation(
            NavigationAction.OpenFilmDetailsFragment(id)
        )
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