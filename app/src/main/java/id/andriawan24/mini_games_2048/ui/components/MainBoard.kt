package id.andriawan24.mini_games_2048.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.andriawan24.mini_games_2048.ui.theme.MiniGames2048Theme
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainBoard() {
    val boxHorizontalPadding by remember { mutableStateOf(16.dp) }
    val tileSpacingSize by remember { mutableStateOf(16.dp) }
    var screenMaxWidth by remember { mutableStateOf(0.dp) }
    val numberTileSize by remember {
        derivedStateOf {
            (screenMaxWidth - (boxHorizontalPadding * 2) - (tileSpacingSize * 3)) / 4
        }
    }

    val boardInitialValues = remember {
        mutableStateListOf(
            mutableStateListOf<Int?>(null, null, null, null),
            mutableStateListOf<Int?>(null, null, null, null),
            mutableStateListOf<Int?>(null, null, null, null),
            mutableStateListOf<Int?>(null, null, null, null)
        )
    }

    BoxWithConstraints {
        screenMaxWidth = this.maxWidth

        LazyColumn(
            modifier = Modifier.padding(boxHorizontalPadding),
            verticalArrangement = Arrangement.spacedBy(tileSpacingSize)
        ) {
            itemsIndexed(boardInitialValues) { _, boardVertical ->
                LazyRow(horizontalArrangement = Arrangement.spacedBy(tileSpacingSize)) {
                    itemsIndexed(boardVertical) { _, tile ->
                        CircleNumberTile(
                            size = numberTileSize,
                            number = tile,
//                            onTileClicked = {
//                                if (boardInitialValues[verticalIndex][index] != null) {
//                                    boardInitialValues[verticalIndex][index] = null
//                                } else {
//                                    boardInitialValues[verticalIndex][index] = 2
//                                }
//                            }
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
            MainBoard()
        }
    }
}