package id.andriawan24.mini_games_2048.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import id.andriawan24.mini_games_2048.ui.theme.MiniGames2048Theme

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    number: Int? = null
) {
    Box(
        modifier = modifier
            .width(size)
            .height(size)
            .clip(MaterialTheme.shapes.small)
            .background(if (number != null) Color(0xFF02B5FF) else Color.LightGray.copy(alpha = 0.2F)),
        contentAlignment = Alignment.Center
    ) {
        if (number != null) {
            Text(
                text = "$number",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Preview
@Composable
fun TileNotNullPreview() {
    MiniGames2048Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Tile(number = 2)
        }
    }
}

@Preview
@Composable
fun TileNullPreview() {
    MiniGames2048Theme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Tile()
        }
    }
}