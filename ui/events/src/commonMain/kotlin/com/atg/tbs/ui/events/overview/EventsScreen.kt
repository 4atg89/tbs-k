package com.atg.tbs.ui.events.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.ui.events.details.EventDetailsScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

object EventsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<EventsScreenModel>()
        val props by screenModel.props
        val effect by screenModel.effect.collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(effect) {
            if (effect == null) return@LaunchedEffect
            when (val e = effect) {
                is DetailsRoute -> navigator.push(EventDetailsScreen(e.event))
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            items(count = props.events.size) { position ->
                val event = props.events[position]
                BorderedCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    EventCard(
                        title = event.title,
                        backgroundUrl = event.banner,
                        imageUrl = event.iconUrl,
                        description = event.description,
                        prize = event.reward.prize,
                        entryCost = event.entryFees.joinToString { "${it.feeCount} | ${it.type}" }
                    ) {
                        props.toDetails(event)
                    }
                }
            }
        }
    }

    @Composable
    private fun BorderedCard(
        modifier: Modifier = Modifier,
        content: @Composable BoxScope.() -> Unit
    ) {
        Box(
            modifier = modifier
                .padding(12.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    ambientColor = Color.White,
                    spotColor = Color.White
                )
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Color(0xFF2C2C2C),
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    private fun EventCard(
        title: String,
        backgroundUrl: String,
        imageUrl: String?,
        description: String,
        prize: String,
        entryCost: String,
        lambda: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clickable { lambda.invoke() },
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (backgroundUrl.isNotEmpty()) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(6.dp))
                            .blur(4.dp),
                        imageModel = { backgroundUrl },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        ),
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (!imageUrl.isNullOrEmpty()) {
                        CoilImage(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            imageModel = { imageUrl },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center,
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            style = MaterialTheme.typography.body1
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Prize: $prize",
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "Entry: $entryCost",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}