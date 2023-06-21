package com.ewingelen.chatter.profile.presentation.contract

import com.ewingelen.chatter.core.data.cloud.model.UserCloud
import com.ewingelen.chatter.core.presentation.BaseCommunication
import com.ewingelen.chatter.core.presentation.ObserveState
import javax.inject.Inject

interface ProfileCommunication : ObserveState<ProfileState> {

    fun showData(user: UserCloud)

    class Base @Inject constructor(): ProfileCommunication,
        BaseCommunication<ProfileState>(defaultState = ProfileState()) {

        override fun showData(user: UserCloud) {
            updateState(
                state.value.copy(
                    name = user.name,
                    surname = user.surname,
                    phoneNumber = user.phoneNumber,
                    photo = user.avatarUrl
                )
            )
        }
    }
}