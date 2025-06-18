package com.atg.tbs.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.atg.tbs.ui.dashboard.tabs.ClanTab
import com.atg.tbs.ui.dashboard.tabs.EventsTab
import com.atg.tbs.ui.dashboard.tabs.HeroesTab
import com.atg.tbs.ui.dashboard.tabs.HomeTab
import com.atg.tbs.ui.dashboard.tabs.OffersTab
import com.atg.tbs.ui.dashboard.tabs.TabNavigationItem
import com.atg.tbs.ui.profile.ProfileScreen

object DashboardScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<DashboardScreenModel>()
        val effect by screenModel.effect.collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow

        DashboardScreen(screenModel.props.value)

        LaunchedEffect(effect) {
            when (effect ?: return@LaunchedEffect) {
                is ProfileRoute -> navigator.push(ProfileScreen())
            }
        }
    }
}

@Composable
private fun DashboardScreen(props: DashboardProps) {
    Column {
        GameTopBar(props)
        TabNavigator(HomeTab) { tabNavigator ->
            Scaffold(
                content = {
                    CurrentTab()
                },
                bottomBar = {
                    BottomNavigation {
                        TabNavigationItem(OffersTab)
                        TabNavigationItem(HeroesTab)
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(ClanTab)
                        TabNavigationItem(EventsTab)
                    }
                }
            )
        }
    }
}

@Composable
private fun GameTopBar(
    props: DashboardProps
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A3C48))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileBox(props)
        InventoryBox(props)
    }
}

@Composable
private fun ProfileBox(props: DashboardProps) {
    if (props.profile == null) return
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "Shield",
            tint = Color.Unspecified,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.width(6.dp))
        Column(modifier = Modifier.clickable { props.openProfileBound.invoke() }) {
            Text(text = props.profile.nickname, color = Color.White, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Trophy",
                    tint = Color.Yellow,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(text = "${props.profile.rating}", color = Color.White)
            }
        }
    }
}

@Composable
private fun InventoryBox(props: DashboardProps) {
    if (props.inventory == null) return
    Row(verticalAlignment = Alignment.CenterVertically) {
        CurrencyBox(icon = Icons.Default.Add, amount = props.inventory.coins, coin = true)
        Spacer(modifier = Modifier.width(8.dp))
        CurrencyBox(icon = Icons.Default.Add, amount = props.inventory.gems, coin = false)

        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Drop",
            tint = Color.Unspecified,
            modifier = Modifier.size(32.dp)
        )
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
