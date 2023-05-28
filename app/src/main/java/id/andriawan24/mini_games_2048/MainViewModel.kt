package id.andriawan24.mini_games_2048

import androidx.lifecycle.ViewModel
import id.andriawan24.mini_games_2048.models.Matrix
import id.andriawan24.mini_games_2048.models.Movement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val _tiles = MutableStateFlow(Matrix())
    val tiles: StateFlow<Matrix> = _tiles.asStateFlow()

    fun newGame() {
        val randomPosition = (0 until _tiles.value.arr.size).random()
        val value = (2 until 4).random()

        val tempArray = _tiles.value.arr.map { it }.toMutableList()

        _tiles.value.arr.replaceAll { null }
        tempArray[randomPosition] = value

        _tiles.update {
            it.matrixCopy(tempArray)
        }
    }

    fun moveTile(movement: Movement) {
        Timber.d("Move to $movement")
    }
}