package com.atg.tbs.ui.heroes

import com.atg.tbs.account.model.HeroEntity
import com.atg.tbs.account.model.ProfileEntity
import com.atg.tbs.account.model.ProfileInventoryEntity
import com.atg.tbs.base.Effect

internal data class HeroesState(
    val heroes: List<HeroEntity>? = null,
)

internal data class HeroesProps(
    val heroes: List<HeroEntity>? = null
)

internal sealed interface HeroesRoute : Effect
