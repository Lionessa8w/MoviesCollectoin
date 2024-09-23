package ru.grebe.moviescollection.filmlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviescollectoin.filmsList.R
import com.example.moviescollectoin.filmsList.databinding.FragmentFilmListBinding
import navigation.NavigationAction
import navigation.NavigationHolder
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import ru.grebe.moviescollection.filmlist.recycler.GenresRecyclerAdapter
import ru.grebe.moviescollection.filmlist.recycler.ImageNameRecyclerAdapter
import ru.grebe.moviescollection.filmdetails.viewmodel.FilmDetailsViewModelState
import ru.grebe.moviescollection.filmlist.viewmodel.FilmsListViewModel
import toolbar.ToolbarHolder

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
            viewModel.getFilmList()
        }
        binding.genresListRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.filmsListRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.listFilmsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FilmDetailsViewModelState.Error -> {
                    showOrHideErrorContainer(true)
                    showOrHideLoadingContainer(false)
                    binding.textError.text = state.message
                }

                FilmDetailsViewModelState.Loading -> {
                    showOrHideErrorContainer(false)
                    showOrHideLoadingContainer(true)
                }

                is FilmDetailsViewModelState.Success -> {
                    showOrHideErrorContainer(false)
                    showOrHideLoadingContainer(false)
                    binding.filmsListRecycler.adapter =
                        ImageNameRecyclerAdapter(
                            filmListModel = state.filmsList,
                            onCardClicked = { id ->
                                openFilmDetailsFragment(id)
                            }
                        )
                    (activity as ToolbarHolder).changeToolbarTitle(state.filmsList.toString())

                    binding.genresListRecycler.adapter =
                        GenresRecyclerAdapter(state.genresList) { genre ->
                            viewModel.setCurrentGenre(genre)

                        }
                }
            }
        }
    }

    private fun openFilmDetailsFragment(id: Int?) {
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

    private fun setColorGenre(isShow: Boolean) {
        val binding = binding ?: return
        binding.genresListRecycler.setBackgroundColor(resources.getColor(R.color.yellow))
    }
}