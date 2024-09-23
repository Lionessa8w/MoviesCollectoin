package ru.grebe.moviescollection.filmlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescollectoin.filmsList.R
import com.example.moviescollectoin.filmsList.databinding.FragmentFilmListBinding
import kotlinx.coroutines.launch
import navigation.NavigationAction
import navigation.NavigationHolder
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.filmlist.recycler.GenresRecyclerAdapter
import ru.grebe.moviescollection.filmlist.recycler.FilmCardRecyclerAdapter
import ru.grebe.moviescollection.filmlist.viewmodel.FilmListViewModelState
import ru.grebe.moviescollection.filmlist.viewmodel.FilmsListViewModel

class FilmsListFragment : Fragment(), KoinComponent {

    private val viewModel: FilmsListViewModel by inject<FilmsListViewModel>()
    private var binding: FragmentFilmListBinding? = null

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

        binding.buttonError.setOnClickListener {
            viewModel.getFilmList(null)
        }
        binding.filmsListRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.genresListRecycler.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewModel.listFilmsState.collect { state ->
                when (state) {
                    is FilmListViewModelState.Error -> {
                        showOrHideErrorContainer(true)
                        showOrHideLoadingContainer(false)
                        binding.textError.text = state.message
                    }

                    FilmListViewModelState.Loading -> {
                        showOrHideErrorContainer(false)
                        showOrHideLoadingContainer(true)
                    }

                    is FilmListViewModelState.Success -> {
                        showOrHideErrorContainer(false)
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

    private fun showOrHideErrorContainer(isShow: Boolean) {
        val binding = binding ?: return
        binding.containerError.isVisible = isShow
    }

    private fun showOrHideLoadingContainer(isShow: Boolean) {
        val binding = binding ?: return
        binding.containerLoading.isVisible = isShow
    }
}