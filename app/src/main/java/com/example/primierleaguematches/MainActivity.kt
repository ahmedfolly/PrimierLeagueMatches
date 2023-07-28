package com.example.primierleaguematches

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.primierleaguematches.domain.models.Matche
import com.example.primierleaguematches.presentation.MatchesState
import com.example.primierleaguematches.presentation.MatchesViewModel
import com.example.primierleaguematches.ui.theme.BlueAqua
import com.example.primierleaguematches.ui.theme.PrimierLeagueMatchesTheme
import com.example.primierleaguematches.ui.theme.Screen
import com.example.primierleaguematches.ui.theme.TextWhite
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MatchesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimierLeagueMatchesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Screen
                ) {
                    if (isNetworkConnected(this)) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            FloatingActionButton(
                                onClick = {
                                    val newActivity =
                                        Intent(this@MainActivity, DownloadAPKsActivity::class.java)
                                    startActivity(newActivity)
                                },
                                containerColor = BlueAqua,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .zIndex(1f)
                                    .align(Alignment.BottomEnd)

                            ) {
                                Icon(
                                    Icons.Filled.Create, contentDescription = "Add",
                                    tint = TextWhite
                                )
                            }
                            DrawMain(viewModel = viewModel)
                        }
                    } else {
                        NoNetworkConnectivity()
                    }

                }
            }
        }
    }
}

@Composable
fun DrawMain(viewModel: MatchesViewModel) {
    val matchesState = viewModel.scheduledMatches.collectAsState()
    val todayMatches = viewModel.todayMatches.collectAsState()

    Column {
        WelcomeCard()
        todayMatches.value.StateValidation(action = { matches ->
            if (matches.isNotEmpty()) {
                TodayMatchesList(matchesList = matches)
            } else {
                EmptyMatchesList()
            }
        }, onErrorAction = { error ->
            OnErrorOccurred(error = error)
        })
        matchesState.value.StateValidation(action = { matches ->
            MatchesList(matchesList = matches)
        }, onErrorAction = { error ->
            OnErrorOccurred(error = error)
        })
    }
}

@Composable
fun MatchesState.StateValidation(
    action: @Composable (List<Matche>) -> Unit,
    onErrorAction: @Composable (String) -> Unit
) {
    when (this) {
        is MatchesState.Error -> {
            onErrorAction(this.e)
        }

        MatchesState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is MatchesState.Success -> {
            action(this.data.matches)
        }
    }
}

@Composable
fun EmptyMatchesList() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(
            text = "Today matches will be added here\n when season initialized :)",
            style = MaterialTheme.typography.bodySmall,
            color = TextWhite,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OnErrorOccurred(error: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = error)
    }
}

@Composable
fun NoNetworkConnectivity() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "There is no network connectivity :( ",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

