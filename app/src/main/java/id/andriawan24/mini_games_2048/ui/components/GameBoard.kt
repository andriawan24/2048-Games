package id.andriawan24.mini_games_2048.ui.components

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.andriawan24.mini_games_2048.MainViewModel
import id.andriawan24.mini_games_2048.models.Matrix
import id.andriawan24.mini_games_2048.models.Movement
import id.andriawan24.mini_games_2048.ui.theme.MiniGames2048Theme
import kotlin.math.abs

@Composable
fun GameBoard(tiles: List<List<Int?>>, onSwipeBoard: (movement: Movement) -> Unit) {

    var swipeDirection by remember { mutableStateOf(Movement.NONE) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, amount ->
                        change.consume()

                        val (x, y) = amount
                        when {
                            x > 5 && abs(x) > abs(y) -> {
                                swipeDirection = Movement.RIGHT
                            }

                            x < -5 && abs(x) > abs(y) -> {
                                swipeDirection = Movement.LEFT
                            }

                            y > 5 && abs(y) > abs(x) -> {
                                swipeDirection = Movement.DOWN
                            }

                            y < -5 && abs(y) > abs(x) -> {
                                swipeDirection = Movement.UP
                            }
                        }
                    },
                    onDragEnd = {
                        onSwipeBoard(swipeDirection)
                    }
                )
            }
    ) {
        Column {
            repeat(Matrix.ROWS_COUNT) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    repeat(Matrix.ROWS_COUNT) { index ->
                        Tile(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(16.dp),
                            number = tiles[row][index]
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainBoardPreview() {
    MiniGames2048Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val viewModel: MainViewModel = viewModel()
            val tiles by viewModel.tiles.collectAsState()
            GameBoard(tiles = tiles.asMatrix(), onSwipeBoard = { })
        }
    }
}