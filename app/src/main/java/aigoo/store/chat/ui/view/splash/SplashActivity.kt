package aigoo.store.chat.ui.view.splash

import aigoo.store.chat.R
import aigoo.store.chat.ui.theme.AGChatTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SplashScreen(
    startLogin: () -> Unit,
    startDashBoard: () -> Unit
) {
    val viewModel = hiltViewModel<SplashViewModel>()
    RenderSplashScreen()
    LaunchedEffect(key1 = true) {
        delay(2000)
        if (viewModel.isAccessToken()) {
            startDashBoard()
        }else {
            startLogin()
        }
    }
    RenderSplashScreen()
}

@Composable
fun RenderSplashScreen() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        ImageBanner()
    }
}
@Composable
fun ImageBanner() {
    Image(painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = "plash logo")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    AGChatTheme {
        RenderSplashScreen()
    }
}