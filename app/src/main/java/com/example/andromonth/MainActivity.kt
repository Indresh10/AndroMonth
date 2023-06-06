package com.example.andromonth

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.andromonth.model.DayRepo
import com.example.andromonth.ui.theme.AndroMonthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroMonthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AndroMonthApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroMonthApp() {
    Scaffold(topBar = { AndroMonthTopBar() }, bottomBar = { AndroMonthBottomBar() }) {
        DayScreenList(days = DayRepo.days, modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroMonthTopBar() {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.days_30),
                style = MaterialTheme.typography.displayLarge
            )
            Icon(
                imageVector = Icons.Filled.Android,
                contentDescription = "android",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    })
}

@Composable
fun AndroMonthBottomBar(modifier: Modifier = Modifier) {
    BottomAppBar(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinkText(
                text = R.string.made,
                highlight = R.string.jetpack,
                link = R.string.jetpack_link,
                modifier = Modifier.padding(2.dp)
            )
            LinkText(
                text = R.string.images_by,
                highlight = R.string.name,
                link = R.string.image_link,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
fun LinkText(
    @StringRes text: Int,
    @StringRes highlight: Int,
    @StringRes link: Int,
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
            append(stringResource(id = text))
            append(' ')
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            appendLink(stringResource(id = highlight), stringResource(id = link))
        }

    }
    ClickableText(text = annotatedString, onClick = { it ->
        annotatedString.onLinkClick(it) {
            ctx.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }, style = MaterialTheme.typography.titleMedium, modifier = modifier)
}

fun AnnotatedString.Builder.appendLink(linkText: String, linkUrl: String) {
    pushStringAnnotation(tag = linkUrl, annotation = linkUrl)
    append(linkText)
    pop()
}

fun AnnotatedString.onLinkClick(offset: Int, onClick: (String) -> Unit) {
    getStringAnnotations(start = offset, end = offset).firstOrNull()?.let {
        onClick(it.item)
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun GreetingPreview() {
    AndroMonthTheme {
        AndroMonthApp()
    }
}