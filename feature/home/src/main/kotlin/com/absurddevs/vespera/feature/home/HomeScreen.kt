import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.absurddevs.vespera.feature.home.R

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,

) {
    HomeScreen(modifier)
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        HomeEmptyScreen(Modifier.align(Alignment.Center))
    }
}

@Composable
private fun HomeEmptyScreen(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.feature_home_no_available_data),
        color = MaterialTheme.colorScheme.onBackground)
}