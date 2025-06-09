package com.atg.tbs.ui.heroes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.account.model.HeroEntity
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

object HeroesScreen : Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<HeroesScreenModel>()
        val navigator = LocalNavigator.currentOrThrow
        HeroesList(screenModel.props.value.heroes ?: emptyList())
    }
}

@Composable
internal fun HeroesList(heroes: List<HeroEntity>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(heroes.size) { HeroCard(heroes[it]) }
    }
}

@Composable
fun HeroCard(hero: HeroEntity) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(72.dp)
            .background(Color.Magenta, shape = RoundedCornerShape(8.dp))
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(Modifier.size(60.dp).background(Color.DarkGray)) {

            CoilImage(
                imageModel = { hero.image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )
        }
        Text("Lvl. ${hero.level}", color = Color.White)
        Text(hero.cardsQuantity.toString(), color = Color.White, fontSize = 12.sp)
    }
}
