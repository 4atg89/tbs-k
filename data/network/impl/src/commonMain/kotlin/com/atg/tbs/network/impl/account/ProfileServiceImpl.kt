package com.atg.tbs.network.impl.account

import com.atg.tbs.network.api.ProfileService
import com.atg.tbs.network.api.dto.ProfileResponse
import com.atg.tbs.network.impl.NetworkContract
import com.atg.tbs.network.impl.baseUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ProfileServiceImpl(private val ktor: HttpClient) : ProfileService {

    private val deleteWhenNginxDone = "http://$baseUrl:5031"

    override suspend fun loadProfile(): ProfileResponse {
        return ktor.get(deleteWhenNginxDone + NetworkContract.Profile.PROFILE).body<ProfileResponse>()
    }

    override suspend fun loadProfileDetails(): ProfileResponse {
        return ktor.get(deleteWhenNginxDone + NetworkContract.Profile.PROFILE_DETAILS).body<ProfileResponse>()
    }

}