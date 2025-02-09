package kz.tz.effectivemobile.ui.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kz.tz.effectivemobile.R
import kz.tz.effectivemobile.ui.models.OfferCard
import kz.tz.effectivemobile.ui.models.VacancyCard
import kz.tz.effectivemobile.ui.navigation.Screens
import kz.tz.effectivemobile.ui.theme.Black
import kz.tz.effectivemobile.ui.theme.Blue
import kz.tz.effectivemobile.ui.theme.Grey1
import kz.tz.effectivemobile.ui.theme.Grey3
import kz.tz.effectivemobile.ui.theme.White
import org.koin.androidx.compose.get


@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = get(),
    navController: NavController
) {
    val vacancies by viewModel.vacancies.collectAsState()
    val offers by viewModel.offers.collectAsState()
    val isLoading by viewModel.isLoading

    var searchQuery by remember { mutableStateOf("") }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        Column {
            //TODO: make separate search result screen
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onBackClick = { navController.popBackStack() },
                numberOfVacancies = vacancies.size,
                isSearchScreen = true,
                hint = "Должность, ключевые слова"
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (offers.isNotEmpty()) { // Ensure vacancies are shown even if offers are empty
                    item {
                        LazyRow(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(offers.size) { index ->
                                val offer = offers[index]
                                OfferCard(offerModel = offer)
                            }
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }

                item {
                    Text(
                        text = "Вакансии для вас",
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp
                    )

                }
                items(vacancies.take(3)) { vacancy ->
                    VacancyCard(
                        vacancyModel = vacancy,
                        toggleFavourite = { viewModel.toggleFavoriteVacancy(it) })
                }
                item {
                    Button(
                        onClick = {
                            navController.navigate(Screens.Vacancies.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = Blue,
                            contentColor = White
                        )
                    ) {
                        Text("Ещё ${formatVacancies(vacancies.size)}")
                    }
                }
            }

        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    numberOfVacancies: Int,
    isSearchScreen: Boolean,
    hint: String
) {
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            //TODO: implement search logic
            TextField(
                value = query,
                onValueChange = onQueryChange,
                modifier = Modifier.weight(0.85f),
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = hint,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Grey1,
                    focusedContainerColor = Grey1,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Grey3,
                    focusedPlaceholderColor = Grey3,
                    unfocusedTextColor = White,
                    focusedTextColor = White,
                    disabledTextColor = White
                ),
                leadingIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = White
                        )
                    }
                }
            )

            Box(
                modifier = Modifier
                    .height(56.dp)
                    .background(Grey1, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }

            }
        }

        if (!isSearchScreen) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = formatVacancies(numberOfVacancies),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 16.8.sp,
                    color = White
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.clickable {
                        Toast.makeText(
                            context,
                            "Text was clicked!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text(
                        text = "По соответсвию",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        lineHeight = 16.8.sp,
                        color = Blue
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_arrange_by),
                        contentDescription = "Filtered by",
                        tint = Blue
                    )
                }

            }
        }
    }
}

fun formatVacancies(vacancies: Int): String {
    val word = when {
        vacancies % 10 == 1 && vacancies % 100 != 11 -> "вакансия"
        vacancies % 10 in 2..4 && vacancies % 100 !in 12..14 -> "вакансии"
        else -> "вакансий"
    }
    return "$vacancies $word"
}

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar(
        query = "something",
        onQueryChange = {},
        onBackClick = {},
        numberOfVacancies = 145,
        isSearchScreen = false,
        hint = "Должность, ключевые слова"
    )
}