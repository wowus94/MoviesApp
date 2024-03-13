package ru.vlyashuk.moviesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.StarHalf
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import ru.vlyashuk.moviesapp.MainViewModel
import ru.vlyashuk.moviesapp.R
import ru.vlyashuk.moviesapp.data.models.Movies
import ru.vlyashuk.moviesapp.navigation.BottomNavItem
import ru.vlyashuk.moviesapp.navigation.Screens
import kotlin.math.ceil
import kotlin.math.floor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allMovies = viewModel.allMovies.observeAsState(listOf()).value
    val navControllerBottomNavItem = rememberNavController()
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.background_color),
                    titleContentColor = Color.White
                ),
                modifier = Modifier.height(40.dp),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }
                }
            )
        },
            bottomBar = {
                BottomNavBar(navController = navControllerBottomNavItem)
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp,
                ),
                content = {
                    items(allMovies.take(40)) { item ->
                        MovieItem(item = item, navController = navController)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .background(colorResource(id = R.color.background_color))
            )
        }
    }
}

@Composable
fun MovieItem(item: Movies, navController: NavController) {
    val paddingStart = Modifier.padding(start = 6.dp)
    val paddingSeparator = Modifier.padding(start = 4.dp, end = 4.dp)
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(Screens.DetailsScreen.route + "/${item.id}")
            }
    ) {
        Image(
            painter = rememberImagePainter(item.image.medium),
            contentDescription = stringResource(id = R.string.image_movies_content_description),
            modifier = Modifier
                .size(250.dp)
                .padding(start = 6.dp, end = 6.dp)
        )
        RatingBar(
            rating = item.rating.average / 2,
            stars = 5,
            modifier = Modifier.padding(start = 6.dp, top = 16.dp, bottom = 7.dp)
        )
        Text(
            text = item.name, fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = paddingStart
        )
        Row(modifier = Modifier.padding(start = 6.dp, bottom = 16.dp)) {
            item.genres.take(1).forEach {
                Text(
                    text = it,
                    color = Color.White
                )
            }
            Text(
                text = "|",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = paddingSeparator
            )
            Text(text = item.status, color = Color.White)
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val listItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile,
    )
    BottomNavigation(
        backgroundColor = Color.Black
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { /*TODO*/ },
                icon = {
                    androidx.compose.material.Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = item.label, fontSize = 12.sp, color = Color.White)
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White
            )
        }
    }
}
