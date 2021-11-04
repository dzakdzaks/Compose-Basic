package com.dzakdzaks.composebasic.ui.screen.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dzakdzaks.composebasic.R
import com.dzakdzaks.composebasic.ui.theme.ComposeBasicTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController, names: List<String> = List(1000) { "Item $it" }) {
    LazyColumn(
        modifier = Modifier.padding()
    ) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                Toast
                    .makeText(context, "$name clicked", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        CardContent(name = name)
    }
}

@Composable
private fun CardContent(name: String) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(4.dp))
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (isExpanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4)
                )
            }
        }
        IconButton(onClick = {
            isExpanded = !isExpanded
        }) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (isExpanded) stringResource(id = R.string.show_less) else stringResource(
                    id = R.string.show_less
                )
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(
    showBackground = true, widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES, name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}