import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tomasmacri.expensapp.ui.theme.getColorsTheme

@Composable
fun LoadingScreen(isLoading: Boolean = false) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.6f)), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                color = getColorsTheme().textColorExpensApp
            )
        }
    }
}
