package com.ewingelen.chatter.main.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.core.presentation.BaseViewModel
import com.ewingelen.chatter.main.domain.MainInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: MainInteractor
) : BaseViewModel<MainState>(defaultState = MainState()) {

    init {
        viewModelScope.launch {
            updateState(
                state.value.copy(
                    userAuthorized = interactor.checkUserAuthorized(),
                    loading = false
                )
            )
        }
    }
}