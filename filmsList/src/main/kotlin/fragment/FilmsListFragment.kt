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
import com.example.moviescollectoin.filmsList.R
import recycler.GenresRecyclerAdapter
import recycler.ImageNameRecyclerAdapter
import viewModel.FilmListViewModelState

class FilmsListFragment : Fragment() {

    private lateinit var listGenres: RecyclerView
    private lateinit var listFilms: RecyclerView
    private lateinit var errorContainer: LinearLayout
    private lateinit var loadingContainer: LinearLayout
    private lateinit var buttonError: Button
    private lateinit var textError: TextView
    private lateinit var imageCatGif: ImageView

//    private var viewModel: FilmsListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        viewModel = ViewModelProvider(this)[FilmsListViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return null
    }
//        val view = inflater.inflate(R.layout.fragment_films, container, false)
//        listGenres = view.findViewById(R.id.genres_list_recycler)
//        listFilms = view.findViewById(R.id.films_list_recycler)
//        errorContainer = view.findViewById(R.id.container_error)
//        textError = view.findViewById(R.id.text_error)
//        buttonError = view.findViewById(R.id.button_error)
//        loadingContainer = view.findViewById(R.id.container_loading)
//        imageCatGif = view.findViewById(R.id.imageView_loading)
//
//        buttonError.setOnClickListener {
//            viewModel?.getFilmList()
//
//        }
//
//        listFilms.layoutManager = GridLayoutManager(context, 2)
//        listGenres.layoutManager = LinearLayoutManager(context)
//
//        viewModel?.listFilmsState?.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is FilmListViewModelState.Error -> {
//                    showOrHideGifLoading(false)
//                    showOrHideErrorContainer(true)
//                    textError.text = state.message
//                }
//
//                FilmListViewModelState.Loading -> {
//                    showOrHideErrorContainer(false)
//                    showOrHideGifLoading(true)
//
//                }
//
//                is FilmListViewModelState.Success -> {
//                    showOrHideErrorContainer(false)
//                    showOrHideGifLoading(false)
//                    listFilms.adapter =
//                        ImageNameRecyclerAdapter(
//                            filmListModel = state.filmsList,
//                            onCardClicked = { id ->
//                                requireActivity().supportFragmentManager.beginTransaction()
//                                    .replace(R.id.container_root, FilmsFragment.newInstance(id))
//                                    .addToBackStack(null).commit()
//                            },
//                            onLikeClicked = { id ->
//                                viewModel?.setLikeOrDelete(id)
//                            }, onIgnoreClicked = { id ->
//                                viewModel?.setIgnore(id)
//                            }
//                        )
//                    listGenres.adapter = GenresRecyclerAdapter(state.genresList) { genre ->
//                        viewModel?.setCurrentGenre(genre)
//                    }
//                }
//            }
//        }
//
//        return view
//    }
//
//    private fun showOrHideErrorContainer(isShow: Boolean) {
//        errorContainer.isVisible = isShow
//    }
//
//    private fun showOrHideGifLoading(isShow: Boolean) {
//        loadingContainer.isVisible = isShow
//        Glide.with(this)
//            .load(R.drawable.cat_dance)
//            .into(imageCatGif)
//    }
}