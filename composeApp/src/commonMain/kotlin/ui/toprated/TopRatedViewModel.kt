package ui.toprated

import data.model.MovieItem
import data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import utils.network.DataState

class TopRatedViewModel : ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val repo = MovieRepository()
    val topRatedMovieResponse = MutableStateFlow<DataState<List<MovieItem>>?>(DataState.Loading)

    fun topRated(page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            repo.topRatedMovie(page).collectLatest {
                topRatedMovieResponse.value = it
            }
        }
    }
}