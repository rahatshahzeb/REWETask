package com.example.rewetask.ui.weatherdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rewetask.R
import com.example.rewetask.db.AppDatabase
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.repository.CityRepository
import com.example.rewetask.ui.composable.EmptyState
import com.example.rewetask.ui.composable.LoadingState
import com.example.rewetask.ui.composable.WeatherData
import com.example.rewetask.ui.navigation.WeatherDetail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    weatherDetail: WeatherDetail,
    navController: NavHostController,
    viewModel: WeatherDetailViewModel,
) {
    val viewState by viewModel.viewState.collectAsState(
        initial = WeatherDetailViewModel.ViewState.Loading
    )
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var isBookmarked by remember { mutableStateOf(weatherDetail.isBookmarked) }
    var title by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    var isRefreshing by remember { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val onRefresh: () -> Unit = {
        isRefreshing = true
    }

    LaunchedEffect(isRefreshing) {
        viewModel.loadWeatherData(weatherDetail.cityName)
        isRefreshing = false
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text( text = title)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            isBookmarked = !isBookmarked
                            viewModel.onUpdateBookmark(
                                cityDetail = LocalCityDetail(
                                    name = weatherDetail.cityName,
                                    countryName = weatherDetail.countryName,
                                    isBookmarked = weatherDetail.isBookmarked
                                )
                            )
                        }
                    }) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Toggle Bookmark"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                when (val state = viewState) {
                    is WeatherDetailViewModel.ViewState.Loading -> {
                        LoadingState()
                    }

                    is WeatherDetailViewModel.ViewState.Empty -> {
                        EmptyState(stringResource(R.string.error_something_went_wrong))
                    }

                    is WeatherDetailViewModel.ViewState.Detail -> {
                        val detail = state.weatherDetail
                        title = detail.cityName

                        PullToRefreshBox(
                            state = pullToRefreshState,
                            isRefreshing = isRefreshing,
                            onRefresh = onRefresh,
                            indicator = {
                                PullToRefreshDefaults.Indicator(
                                    state = pullToRefreshState,
                                    isRefreshing = isRefreshing,
                                    modifier = Modifier.align(Alignment.TopCenter),
                                )
                            }
                        ){
                            WeatherData(detail)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherDataPreview() {
    val context = LocalContext.current
    val cityDao = AppDatabase.getDatabase(context).cityDao()
    val viewModel = viewModel {
        WeatherDetailViewModel(
            cityName = "Vienna",
            weatherDao = AppDatabase.getDatabase(context).weatherDao(),
            cityDao = AppDatabase.getDatabase(context).cityDao(),
            cityRepository = CityRepository(cityDao = cityDao),
        )
    }
    WeatherDetailScreen(
        weatherDetail = WeatherDetail(),
        navController = rememberNavController(),
        viewModel = viewModel,
    )
}