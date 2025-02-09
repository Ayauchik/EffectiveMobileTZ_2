package kz.tz.effectivemobile.ui.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.tz.domain.models.VacancyModel
import kz.tz.effectivemobile.MainViewModel
import kz.tz.effectivemobile.ui.models.VacancyCard
import kz.tz.effectivemobile.ui.search.formatVacancies
import kz.tz.effectivemobile.ui.theme.Grey3
import kz.tz.effectivemobile.ui.theme.White
import org.koin.androidx.compose.get


@Composable
fun FavouriteScreen(
    viewModel: FavouriteScreenViewModel = get(),
    mainViewModel: MainViewModel = get()
) {
    val vacancies by viewModel.favVacancies.collectAsState()
    val isLoading by viewModel.isLoading
//
//    LaunchedEffect(Unit) {
//        mainViewModel.fetchFavoriteVacanciesCount()
//    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
        ) {
            item {
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Избранное",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        color = White
                    )
                    Spacer(modifier = Modifier.size(24.dp))

                    Text(
                        text = formatVacancies(vacancies.size),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 16.8.sp,
                        color = Grey3
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }

            }
            items(vacancies, key = { it.id }) { vacancy ->
                VacancyCard(
                    vacancyModel = vacancy,
                    toggleFavourite = { viewModel.toggleFavoriteVacancy(it) }
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }


}

@Preview
@Composable
fun PreviewScreensaver() {
    FavouriteScreen()
}