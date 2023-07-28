package com.example.primierleaguematches

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.primierleaguematches.domain.models.Matche
import com.example.primierleaguematches.ui.theme.BlueAqua
import com.example.primierleaguematches.ui.theme.CardOnScreen
import com.example.primierleaguematches.ui.theme.TextWhite

@Composable
fun MatchContainer(
    homeTeamName: String,
    homeTeamImage: String,
    awayTeamName: String,
    awayTeamImage: String,
    matchIsoTimeStamp: String
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .heightIn(70.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(CardOnScreen),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TeamSession(imageModel = homeTeamImage, teamName = homeTeamName)
            TimeSession(matchIsoTimeStamp)
            TeamSession(imageModel = awayTeamImage, teamName = awayTeamName)
        }
    }
}

@Composable
fun TeamSession(imageModel: String, teamName: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageModel, contentDescription = "",
            modifier = Modifier
                .size(64.dp, 64.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = teamName, style = MaterialTheme.typography.bodySmall, maxLines = 1,
            modifier = Modifier
                .padding(top = 8.dp)
                .width(100.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TimeSession(matchIsoTimeStamp: String) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
        ) {
            val (date, time) = convertIsoToDateTime(matchIsoTimeStamp)
            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.width(64.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = date, style = MaterialTheme.typography.labelSmall)
        }
    }

@Composable
fun WelcomeCard() {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(BlueAqua),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.pl_fade),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp, 140.dp)
                    .align(Alignment.CenterEnd),
            )
            Text(
                text = "For a better live score experience in your device, level up your watch quality.",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp)
                    .zIndex(1f)
            )
        }
    }
}

@Composable
fun MatchesList(matchesList: List<Matche>) {
    Column {
        Text(
            text = "Upcoming matches",
            color = TextWhite,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        LazyColumn {
            items(matchesList) { matche ->
                MatchContainer(
                    matche.homeTeam.shortName,
                    matche.homeTeam.crest,
                    matche.awayTeam.shortName,
                    matche.awayTeam.crest,
                    matche.utcDate
                )
            }
        }
    }

}

@Composable
fun TodayMatchContainer(
    homeTeamName: String,
    homeTeamImage: String,
    awayTeamName: String,
    awayTeamImage: String,
    matchIsoTimeStamp: String
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .height(120.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(CardOnScreen),
        shape = RoundedCornerShape(30.dp)
    )
    {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.pl_fade2),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp, 140.dp)
                    .align(Alignment.CenterEnd)
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .zIndex(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TeamSession(imageModel = homeTeamImage, teamName = homeTeamName)
                TimeSession(matchIsoTimeStamp)
                TeamSession(imageModel = awayTeamImage, teamName = awayTeamName)
            }
        }
    }
}

@Composable
fun TodayMatchesList(matchesList: List<Matche>) {
    Column() {
        Text(
            text = "Today matches",
            color = TextWhite,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow {
            items(matchesList) { match ->
                TodayMatchContainer(
                    match.homeTeam.shortName,
                    match.homeTeam.crest,
                    match.awayTeam.shortName,
                    match.awayTeam.crest,
                    match.utcDate
                )
            }
        }
    }

}