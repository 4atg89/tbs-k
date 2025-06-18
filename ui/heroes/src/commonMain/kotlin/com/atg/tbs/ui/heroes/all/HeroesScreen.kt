package com.atg.tbs.ui.heroes.all

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.atg.tbs.account.model.HeroEntity
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

object HeroesScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<HeroesScreenModel>()
        HeroesList(screenModel.props.value.heroes ?: emptyList())
    }
}

@Composable
internal fun HeroesList(heroes: List<HeroEntity>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(heroes.size) {
            Box {
                val hero = heroes[it]
                HeroCard(hero)
                if (hero.level > 0) {
                    CardCounter(
                        "${heroes[it].heroCards}/${heroes[it].nextLevelCards}",
                        Modifier.align(Alignment.TopEnd)
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroCardDialog(hero: HeroEntity, showDialog: Boolean, dismissDialog: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = dismissDialog,
            title = { Text(hero.name) },
            text = {
                CoilImage(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .aspectRatio(16f / 9f)
                        .blur(if (hero.level > 0) 0.dp else 1.dp),
                    imageModel = { hero.image },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        alpha = if (hero.level > 0) DefaultAlpha else 0.5f
                    ),
                )
            },
            confirmButton = {
                TextButton(onClick = dismissDialog) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissDialog) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
internal fun HeroCard(hero: HeroEntity) {
    val showDialog = remember { mutableStateOf(false) }
    HeroCardDialog(hero, showDialog.value, { showDialog.value = false })
    Column(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp, top = 2.dp)
            .fillMaxWidth()
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(start = 2.dp, end = 2.dp, top = 2.dp)
            .clickable { showDialog.value = true },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            Modifier.padding(2.dp)
                .fillMaxWidth()
        ) {

            CoilImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .aspectRatio(9f / 11f)
                    .blur(if (hero.level > 0) 0.dp else 4.dp),
                imageModel = { hero.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    alpha = if (hero.level > 0) DefaultAlpha else 0.1f
                ),
            )

            Text(
                "Lvl. ${hero.level}",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter)
                    .background(color = Color(0x888A8A8A))
            )
        }
    }
}

@Composable
private fun CardCounter(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .size(32.dp)
            .padding(2.dp)
            .background(Color.Black, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .background(Color.Black, shape = RoundedCornerShape(8.dp))
        )
    }
}
