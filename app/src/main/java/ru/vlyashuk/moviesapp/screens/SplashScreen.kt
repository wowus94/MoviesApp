package ru.vlyashuk.moviesapp.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import ru.vlyashuk.moviesapp.R
import ru.vlyashuk.moviesapp.navigation.Screens
import ru.vlyashuk.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimate by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimate) 1f else 0f,
        animationSpec = tween(durationMillis = 3000), label = ""
    )
    LaunchedEffect(key1 = true) {
        startAnimate = true
        delay(4000)
        navController.navigate(Screens.Main.route)
    }
    Splash(alpha = alphaAnimation.value)
}

@Composable
fun Splash(alpha: Float) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.Black
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(140.dp)
                    .alpha(alpha = alpha),
                painter = painterResource(id = R.drawable.splash_icon),
                contentDescription = stringResource(
                    R.string.splash_icon
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSplash() {
    MoviesAppTheme {
        Splash(1f)
    }
}