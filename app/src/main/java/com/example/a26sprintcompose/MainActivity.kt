package com.example.a26sprintcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.text.ifEmpty

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(
                Contact(
                    name = "Моника",
                    surname = "Анна Мария",
                    familyName = "Беллуччи",
                    imageRes = R.drawable.monika,
                    isFavorite = true,
                    phone = "+7 495 495 95 95",
                    address = "Москва, улица Смоленская, дом 12, квартира 12",
                    email = "monika@bellucci.com"
                )
            )
        }
    }

    @Composable
    private fun ContactDetails(contact: Contact) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            if (contact.imageRes !== null) PersonPhoto() else PlaceholderPhotoContact(
                contact.name,
                contact.familyName
            )
            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(text = contact.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = contact.surname ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Row {
                Text(text = contact.familyName, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                if (contact.isFavorite) Image(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier.padding(top = 30.dp)
            ) {

                InfoRow(
                    label = stringResource(R.string.phone), value = contact.phone.ifEmpty { "---" },
                )


                InfoRow(
                    label = stringResource(R.string.address), value = contact.address,
                )
                contact.email?.let {
                    InfoRow(
                        label = stringResource(R.string.email),
                        value = contact.email
                    )

                }
            }
        }


    }

    @Composable
    private fun PersonPhoto() {
        Image(
            modifier = Modifier.size(240.dp, 120.dp),
            painter = painterResource(R.drawable.monika),
            contentDescription = null
        )
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreview() {
        ContactDetails(
            Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "Москва, улица Смоленская, дом 12, квартира 12",
                email = "monika@bellucci.com"
            )
        )
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsWithoutFavoriteEmailPreview() {
        ContactDetails(
            Contact(
                name = "Моника",
                surname = null,
                familyName = "Беллуччи",
                imageRes = R.drawable.monika,
                isFavorite = false,
                phone = "",
                address = "г. Москва, улица Смоленская, дом 12, квартира 1",
                email = null
            )
        )
    }

    @Composable
    private fun PlaceholderPhotoContact(name: String, familyName: String) {
        Box(
            modifier = Modifier
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle), modifier = Modifier.size(60.dp),
                contentDescription = null,
                tint = Color.LightGray
            )
            Text(name.take(1) + familyName.take(1), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }

    @Composable
    private fun InfoRow(
        label: String,
        value: String,
        modifier: Modifier = Modifier,
        labelStyle: TextStyle = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic),
        valueStyle: TextStyle = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Normal)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),

            ) {
            Text(
                text = "$label: ", style = labelStyle, modifier = Modifier.padding(start = 70.dp)
            )

            Text(
                text = value,
                style = valueStyle,
                modifier = Modifier.padding(start = 8.dp),
                textAlign = TextAlign.Start
            )

        }
    }
}

