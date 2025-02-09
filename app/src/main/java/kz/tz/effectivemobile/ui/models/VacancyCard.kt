package kz.tz.effectivemobile.ui.models

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.tz.domain.models.VacancyModel
import kz.tz.effectivemobile.R
import kz.tz.effectivemobile.ui.theme.Blue
import kz.tz.effectivemobile.ui.theme.Green
import kz.tz.effectivemobile.ui.theme.Grey1
import kz.tz.effectivemobile.ui.theme.Grey3
import kz.tz.effectivemobile.ui.theme.Grey4
import kz.tz.effectivemobile.ui.theme.White

@Composable
fun VacancyCard(
    vacancyModel: VacancyModel,
    toggleFavourite: (VacancyModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth(0.95f)
                ) {
                    Text(
                        text = "Сейчас просматривает ${vacancyModel.lookingNumber} человек",
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 16.8.sp,
                        color = Green
                    )

                    Text(
                        text = vacancyModel.title,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        lineHeight = 19.2.sp,
                        color = Color.White
                    )

                    Text(
                        text = vacancyModel.town,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 16.8.sp,
                        color = Color.White
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = vacancyModel.company,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 16.8.sp,
                            color = Color.White
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_verification),
                            contentDescription = "Verification",
                            tint = Grey3,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_workcase),
                            contentDescription = "Work",
                            tint = White,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = vacancyModel.experiencePreviewText,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            lineHeight = 16.8.sp,
                            color = Color.White
                        )
                    }


                    val publicationText = formatDateAlternative(vacancyModel.publishedDate)

                    Text(
                        text = publicationText,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 16.8.sp,
                        color = Grey3
                    )
                }


                var isFavorite by remember { mutableStateOf(vacancyModel.isFavorite) }
                val favoriteIconRes =
                    if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart
                val tintColor = if (isFavorite) Blue else Grey4

                Icon(
                    painter = painterResource(favoriteIconRes),
                    contentDescription = "Favourite",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isFavorite = !isFavorite
                            toggleFavourite(vacancyModel)
                        },
                    tint = tintColor
                )

            }

            Spacer(modifier = Modifier.size(21.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                )
            ) {
                Text(
                    text = "Откликнутся",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 18.2.sp,
                    color = Color.White
                )
            }
        }
    }

}

fun formatDateAlternative(dateString: String): String {
    val parts = dateString.split("-")
    val year = parts[0].toInt()
    val month = parts[1].toInt()
    val day = parts[2].toInt()

    val months = arrayOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val monthName = months[month - 1]
    return "Опубликовано $day $monthName"
}


@Preview
@Composable
fun PreviewVacancyCard() {

    val vacancyModel = VacancyModel(
        lookingNumber = 1,
        isFavorite = true,
        title = "UX/UI Designer",
        town = "Минск",
        company = "Мобирикс",
        experiencePreviewText = "Опыт от 1 года до 3 лет",
        publishedDate = "2024-02-20",
        id = "something"
    )

//    VacancyCard(
//        vacancyModel
//    )
}