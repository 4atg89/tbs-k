package com.atg.tbs.ui.profile

import com.atg.tbs.account.model.ProfileChallengesEntity
import com.atg.tbs.account.model.ProfileClanEntity
import com.atg.tbs.account.model.ProfileEntity
import com.atg.tbs.account.model.ProfileInventoryEntity
import com.atg.tbs.account.model.ProfileStatisticsEntity
import com.atg.tbs.account.model.UserProfileDetailsEntity
import com.atg.tbs.base.Effect

internal data class ProfileState(
    val profile: UserProfileDetailsEntity? = null
)

internal data class ProfileProps(
    val profile: ProfileEntity? = null,
    val inventory: ProfileInventoryEntity? = null,
    val clan: ProfileClanEntity? = null,
    val statistics: ProfileStatisticsEntity? = null,
    val challenges: ProfileChallengesEntity? = null,
    val backBound: () -> Unit = {}
)

internal sealed interface ProfileRoute : Effect

data object BackRoute : ProfileRoute
