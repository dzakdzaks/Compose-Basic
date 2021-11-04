package com.dzakdzaks.composebasic.ui.screen.favorite

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dzakdzaks.composebasic.ui.theme.ComposeBasicTheme

@Composable
fun FavoriteScreen(navController: NavController, onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Favorite", color = MaterialTheme.colorScheme.onPrimary)
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }
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
        FavoriteScreen(navController = NavController(LocalContext.current)) {}
    }
}