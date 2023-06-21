package com.ewingelen.settings.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.ewingelen.chatter.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : BaseViewModel<Boolean>(false) {

    fun selectLanguage(english: Boolean) {
        updateState(english)
        val languageTags = if (english) "en-EN" else "ua-UA"
        val appLocale = LocaleListCompat.forLanguageTags(languageTags)
        Timber.d(languageTags.toString())
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}