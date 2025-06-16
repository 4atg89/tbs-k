package com.atg.tbs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

object HomeScreen: Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFB3E5FC)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color(0xFF1976D2),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Win 5 matches!", color = Color.White, fontWeight = FontWeight.Bold)
                    LinearProgressIndicator(
                        progress = 0.4f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .padding(vertical = 4.dp),
                        color = Color.Yellow
                    )
                    Text("2/5", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(Color(0xFFFFF9C4), RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Arena", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xFF8D6E63))
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {}, modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text("Battle")
                }
                Button(onClick = {}, modifier = Modifier.weight(1f).padding(start = 8.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA726))) {
                    Text("Play Skillshot!")
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(4) { idx ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = if (idx == 1) Color(0xFFFFF176) else Color(0xFF81D4FA),
                        modifier = Modifier.size(64.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("Open\nNow", textAlign = TextAlign.Center, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}