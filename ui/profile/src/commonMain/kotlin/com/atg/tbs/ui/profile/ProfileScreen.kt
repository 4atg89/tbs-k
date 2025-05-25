package com.atg.tbs.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ProfileScreenModel>()
        val effect by screenModel.effect.collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow

        PlayerProfileScreen(screenModel.props.value)

        LaunchedEffect(effect) {
            when (effect ?: return@LaunchedEffect) {
                is ProfileRoute -> navigator.push(ProfileScreen())
            }
        }
    }

    @Composable
    private fun PlayerProfileScreen(props: ProfileProps) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1A3B5C))
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileSection(props)
            Spacer(modifier = Modifier.height(16.dp))
            ClanSection(props)
            Spacer(modifier = Modifier.height(16.dp))
            DeckSection()
            Spacer(modifier = Modifier.height(16.dp))
            StatsSection(props)
            Spacer(modifier = Modifier.height(16.dp))
            ChallengeSection(props)
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { }) {
                    Text("Back")
                }
                Button(onClick = { }) {
                    Text("Ok")
                }
            }
        }
    }

    @Composable
    private fun ProfileSection(props: ProfileProps) {
        if (props.profile == null) return
        Column {
            Text(
                props.profile.nickname,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TrophyCard("Rating", props.profile.rating, Color(0xFFEE8844))
//                TrophyCard("Last Season Rating", props.profile.rating, Color(0xFFAAAAAA), position = 14950)
            }
        }
    }

    @Composable
    private fun TrophyCard(label: String, trophies: Int, bgColor: Color, position: Int? = null) {
        Column(
            modifier = Modifier
                .background(bgColor.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(label, color = Color.White, fontSize = 14.sp)
            Text(
                "🏆 $trophies",
                color = Color.Yellow,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            position?.let {
                Text("Position: $it", color = Color.White, fontSize = 12.sp)
            }
        }
    }

    @Composable
    private fun ClanSection(props: ProfileProps) {
        if (props.clan == null) return
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF2D4B6A), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(props.clan.name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Leader", color = Color.White, fontSize = 14.sp)
        }
    }

    @Composable
    private fun DeckSection() {
        Column {
            Text("Deck", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.DarkGray),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            "Lvl. ${if (it % 2 == 0) 22 else 21}",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.BottomCenter).padding(4.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun StatsSection(props: ProfileProps) {
        if (props.statistics == null) return
        Column {
            Text(
                "Main Statistics",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                val stats = listOf(
                    "Wins" to props.statistics.wins,
                    "Max Rating" to props.statistics.maxRating,
                    "Epic Wins" to props.statistics.epicWins,
                    "Open Cards" to 2554,
                    "Killed enemies" to 31406
                )
                stats.chunked(3).forEach { row ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        row.forEach { (label, value) ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(label, color = Color.LightGray, fontSize = 12.sp)
                                Text(value.toString(), color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    @Composable
    private fun ChallengeSection(props: ProfileProps) {
        if (props.challenges == null) return
        Column {
            Text("Challenges", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Max Wins", color = Color.LightGray, fontSize = 12.sp)
                    Text(props.challenges.winStreak.toString(), color = Color.White, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Challenges amount", color = Color.LightGray, fontSize = 12.sp)
                    Text(props.challenges.battlesCount.toString(), color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

}