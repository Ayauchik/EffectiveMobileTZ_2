package kz.tz.effectivemobile.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kz.tz.effectivemobile.ui.models.VacancyCard
import kz.tz.effectivemobile.ui.theme.Blue
import kz.tz.effectivemobile.ui.theme.White
import org.koin.androidx.compose.get

@Composable
fun VacanciesByComplianceScreen(
    viewModel: SearchScreenViewModel = get(),
    navController: NavController
) {

    val vacancies by viewModel.vacancies.collectAsState()
    val isLoading by viewModel.isLoading

    var searchQuery by remember { mutableStateOf("") }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        Column {

            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = { navController.popBackStack() },
                numberOfVacancies = vacancies.size,
                isSearchScreen = false,
                hint = "Должность по подходящим вакансиям"
            )
            Spacer(modifier = Modifier.size(24.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(vacancies) { vacancy ->
                    VacancyCard(
                        vacancyModel = vacancy,
                        toggleFavourite = { viewModel.toggleFavoriteVacancy(it) })
                }
            }

        }

    }
}
