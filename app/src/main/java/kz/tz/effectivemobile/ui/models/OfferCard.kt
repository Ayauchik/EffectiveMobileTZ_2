package kz.tz.effectivemobile.ui.models

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.tz.domain.models.OfferModel
import kz.tz.effectivemobile.R
import kz.tz.effectivemobile.ui.theme.DarkBlue
import kz.tz.effectivemobile.ui.theme.DarkGreen
import kz.tz.effectivemobile.ui.theme.Green
import kz.tz.effectivemobile.ui.theme.Grey1

@Composable
fun OfferCard(
    offerModel: OfferModel
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .size(width = 132.dp, height = 120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey1
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = {
//            Toast.makeText(
//                context,
//                "Offer clicked, and goes with this link: ${offerModel.link}",
//                Toast.LENGTH_SHORT
//            ).show()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(offerModel.link))
            context.startActivity(intent)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 10.dp, end = 12.dp, bottom = 11.dp)
        ) {

            if (offerModel.id == "level_up_resume" || offerModel.id == "temporary_job") {
                val painter =
                    when (offerModel.id) {
                        "level_up_resume" -> R.drawable.ic_star
                        "temporary_job" -> R.drawable.ic_list
                        else -> null
                    }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .background(DarkGreen),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(id = painter!!),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Green,
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .background(DarkBlue)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            val maxLines = if (offerModel.button != null) 2 else 3

            //TODO: 3 lines of text//
            Text(
                text = offerModel.title,
                fontWeight = FontWeight.W500,
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 16.8.sp,
                maxLines = maxLines
            )

            offerModel.button?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.W400,
                    color = Green,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

fun truncateText(text: String, maxLines: Int): String {
    val words = text.split(" ")
    var truncatedText = ""
    var currentLength = 0
    val maxLength = maxLines * 30 // Approximate character count per line

    for (word in words) {
        if (currentLength + word.length > maxLength) break
        truncatedText += "$word "
        currentLength += word.length + 1
    }

    return truncatedText.trim() + if (truncatedText.length < text.length) "..." else ""
}

@Preview
@Composable
fun PreviewOfferCard() {
    val offerModel = OfferModel(
        id = "level_up_resume",
        title = "Поднять резюме в поиске",
        link = "https://hh.ru/",
        button = "Поднять"
    )

    OfferCard(offerModel)
}