package com.example.rewetask.ui.searchcity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rewetask.R
import com.example.rewetask.db.AppDatabase
import com.example.rewetask.ui.composable.CityList
import com.example.rewetask.ui.composable.EmptyState
import com.example.rewetask.ui.composable.LoadingState
import com.example.rewetask.ui.composable.SearchBar
import com.example.rewetask.ui.navigation.WeatherDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCityScreen(
    navController: NavHostController,
    viewModel: SearchCityViewModel,
) {
    val viewState: CityListViewState by viewModel.viewState.collectAsState(
        initial = CityListViewState.Loading
    )
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    // No title
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                SearchBar(
                    hint = stringResource(R.string.search),
                    onTextChange = {
                        searchQuery = it
                        viewModel.onSearch(searchQuery)
                                   },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (val state = viewState) {
                    is CityListViewState.Loading -> { LoadingState() }
                    is CityListViewState.Empty -> { EmptyState(stringResource(R.string.search_bookmark_city)) }
                    is CityListViewState.CityList -> {
                        CityList(
                            onNavigateToDetail = {
                                navController.navigate(
                                route = WeatherDetail(
                                    cityName = it.name,
                                    countryName = it.countryName,
                                    isBookmarked = it.isBookmarked
                                )
                            )},
                            onBookmarkClick = { city -> viewModel.onUpdateBookmark(city) },
                            viewState = state,
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SearchCityPreview() {
    val context = LocalContext.current
    val viewModel = viewModel {
        SearchCityViewModel(AppDatabase.getDatabase(context).cityDao())
    }
    SearchCityScreen(
        viewModel = viewModel,
        navController = rememberNavController()
    )
}
