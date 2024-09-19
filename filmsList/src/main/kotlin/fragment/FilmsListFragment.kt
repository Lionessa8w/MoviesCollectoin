package fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviescollectoin.filmsList.R
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent
import recycler.GenresRecyclerAdapter
import recycler.ImageNameRecyclerAdapter
import viewModel.FilmListViewModelState
import viewModel.FilmsListViewModel

class FilmsListFragment : Fragment(), KoinComponent {

    private lateinit var listGenres: RecyclerView
    private lateinit var listFilms: RecyclerView
    private lateinit var errorContainer: LinearLayout
    private lateinit var loadingContainer: LinearLayout
    private lateinit var buttonError: Button
    private lateinit var textError: TextView
    private lateinit var imageCatGif: ImageView

    private val viewModel: FilmsListViewModel by inject<FilmsListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fiim_list, container, false)
        listGenres = view.findViewById(R.id.genres_list_recycler)
        listFilms = view.findViewById(R.id.films_list_recycler)
        errorContainer = view.findViewById(R.id.container_error)
        textError = view.findViewById(R.id.text_error)
        buttonError = view.findViewById(R.id.button_error)
        loadingContainer = view.findViewById(R.id.container_loading)
        imageCatGif = view.findViewById(R.id.imageView_loading)

        buttonError.setOnClickListener {
            viewModel.getFilmList()

        }

        listFilms.layoutManager = GridLayoutManager(context, 2)
        listGenres.layoutManager = LinearLayoutManager(context)

        viewModel.listFilmsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FilmListViewModelState.Error -> {
                    showOrHideErrorContainer(true)
                    textError.text = state.message
                }

                FilmListViewModelState.Loading -> {
                    showOrHideErrorContainer(false)
                }

                is FilmListViewModelState.Success -> {
                    showOrHideErrorContainer(false)
                    listFilms.adapter =
                        ImageNameRecyclerAdapter(
                            filmListModel = state.filmsList,
                            onCardClicked = { id ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.container_root, FilmsFragment.newInstance(id))
                                    .addToBackStack(null).commit()
                            }
                        )
                    listGenres.adapter = GenresRecyclerAdapter(state.genresList) { genre ->
                        viewModel.setCurrentGenre(genre)
                    }
                }
            }
        }

        return view
    }

    private fun showOrHideErrorContainer(isShow: Boolean) {
        errorContainer.isVisible = isShow
    }

    private fun setColorGenre(isShow: Boolean){
        listGenres.setBackgroundColor(resources.getColor(R.color.yellow))
    }
}