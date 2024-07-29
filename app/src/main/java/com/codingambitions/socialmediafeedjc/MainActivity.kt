package com.codingambitions.socialmediafeedjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingambitions.socialmediafeedjc.ui.theme.SocialMediaFeedJetpackComposeTheme

class MainActivity : AppCompatActivity() {

    val languageChangeHelper by lazy {
        LanguageChangeHelper()
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SocialMediaFeedJetpackComposeTheme {

                val allLanguages = listOf(
                    Language("en", "English", R.drawable.uk),
                    Language("hi", "Hindi", R.drawable.indian),
                    Language("fr", "French", R.drawable.france),
                )

                val currentLanguageCode: String = languageChangeHelper.getLanguageCode(applicationContext)

                var currentLanguage by remember { mutableStateOf(currentLanguageCode) }

                val onCurrentLanguageChange: (String) -> Unit = { newLanguage ->
                    currentLanguage = newLanguage
                    languageChangeHelper.changeLanguage(applicationContext, newLanguage)
                }


                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        stickyHeader {
                            Column {
                                Row(
                                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        stringResource(id = R.string.app_title),
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.background)
                                            .padding(16.dp),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(modifier = Modifier.weight(1f))

                                    LanguagesDropdown(
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.background)
                                            .padding(top = 8.dp),
                                        languagesList = allLanguages,
                                        currentLanguage,
                                        onCurrentLanguageChange
                                    )
                                }

                                HorizontalDivider()
                            }

                        }
                        items(5) {
                            SocialMediaPost()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LanguagesDropdown(
    modifier: Modifier = Modifier,
    languagesList: List<Language>,
    currentLanguage: String,
    onCurrentLanguageChange: (String) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(languagesList.first { it.code == currentLanguage }) }

    Box(
        modifier = modifier
            .padding(end = 16.dp)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        Row(
            modifier = Modifier
                .height(24.dp)
                .clickable {
                    expanded = !expanded
                }
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageListItem(selectedItem)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            repeat(languagesList.size) {
                val item = languagesList[it]
                DropdownMenuItem(text = {
                    LanguageListItem(selectedItem = item)
                }, onClick = {
                    selectedItem = item
                    expanded = !expanded
                    onCurrentLanguageChange(selectedItem.code)
                })
            }

        }
    }


}


data class Language(
    val code: String,
    val name: String,
    @DrawableRes val flag: Int
)

@Composable
fun LanguageListItem(selectedItem: Language) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape),
            painter = painterResource(selectedItem.flag),
            contentScale = ContentScale.Crop,
            contentDescription = selectedItem.code
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = selectedItem.name,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SocialMediaFeedJetpackComposeTheme {
        //Greeting("Android")
    }
}