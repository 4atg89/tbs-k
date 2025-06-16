package com.atg.tbs.ui.events.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.base.view.GoBack
import com.atg.tbs.ui.events.overview.EventCardState

internal class EventDetailsScreen(private val event: EventCardState) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column {
            GoBack { navigator.pop() }
            ClassicChallengeScreen()
        }
    }
}


@Composable
fun ClassicChallengeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E90FF)) // Синий фон
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Classic event",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color.Yellow,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color(0xFF388E3C), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("⚔️", fontSize = 40.sp, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Description of the event",
            style = MaterialTheme.typography.body1,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Row {
                repeat(12) {
                    Box(
                        modifier = Modifier
                            .size(18.dp, 16.dp)
                            .background(Color(0xFF222222), shape = RoundedCornerShape(3.dp))
                            .padding(1.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Row {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(16.dp, 16.dp)
                            .background(Color(0xFFB71C1C), shape = RoundedCornerShape(3.dp))
                            .padding(1.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Wins: 0 out of 12", color = Color(0xFF00FF00), fontSize = 14.sp)
            Text("Loses", color = Color(0xFFFF4444), fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(12.dp),
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Prize", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("\uD83D\uDCB0 4 000", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("\uD83D\uDCC4 80", fontSize = 16.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF43A047)),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Participate  10\uD83D\uDC8E", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Participate", color = Color.DarkGray, fontSize = 16.sp)
        }
    }
}