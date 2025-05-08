package com.atg.tbs.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.jetbrains.compose.resources.painterResource

object DashboardScreen : Screen {

    @Composable
    override fun Content() {
        val a = koinScreenModel<DashboardScreenModel>()
        GameTopBar()
    }
}

@Composable
private fun GameTopBar() {
    Column {
        GameTopBar("4atg89", 1500, 1020, 1000)
    }
}

@Composable
private fun GameTopBar(
    username: String,
    trophies: Int,
    coins: Int,
    gems: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1A3C48))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Shield",
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(6.dp))
            Column {
                Text(text = username, color = Color.White, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Trophy",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(text = "$trophies", color = Color.White)
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            CurrencyBox(icon = Icons.Default.Add, amount = coins, coin = true)
            Spacer(modifier = Modifier.width(8.dp))
            CurrencyBox(icon = Icons.Default.Add, amount = gems, coin = false)

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Drop",
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun CurrencyBox(icon: ImageVector, amount: Int, coin: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color(0xFF0F2A33), RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Add",
            tint = Color.Green,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(text = "$amount", color = Color.White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = if (coin) "Coins" else "Gems",
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )
    }
}
