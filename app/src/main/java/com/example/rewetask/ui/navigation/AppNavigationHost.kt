package com.example.rewetask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rewetask.db.AppDatabase
import com.example.rewetask.ui.searchcity.SearchCityScreen
import com.example.rewetask.ui.searchcity.SearchCityViewModel
import com.example.rewetask.ui.weatherdetail.WeatherDetailScreen
import com.example.rewetask.ui.weatherdetail.WeatherDetailViewModel

@Composable
fun AppNavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = SearchCity) {
        composable<SearchCity> {
            val searchCityViewModel: SearchCityViewModel = viewModel {
                SearchCityViewModel(
                    cityDao = AppDatabase.getDatabase(context).cityDao()
                )
            }
            SearchCityScreen(
                navController = navController,
                viewModel = searchCityViewModel,
            )
        }
        composable<WeatherDetail> { backStackEntry ->
            val weatherDetail: WeatherDetail = backStackEntry.toRoute()

            val weatherDetailViewModel: WeatherDetailViewModel = viewModel {
                WeatherDetailViewModel(
                    cityName = weatherDetail.cityName,
                    cityDao = AppDatabase.getDatabase(context).cityDao(),
                    weatherDao = AppDatabase.getDatabase(context).weatherDao()
                )
            }

            WeatherDetailScreen(
                weatherDetail = weatherDetail,
                navController = navController,
                viewModel = weatherDetailViewModel
            )
        }
    }
}
