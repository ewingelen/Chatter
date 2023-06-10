package com.ewingelen.chatter.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.core.presentation.authGraph

@Composable
fun AppNavHost(
    userAuthorized: Boolean,
    verifyPhoneNumber: (VerifyPhoneNumber) -> Unit,
    showSnackbar: (String) -> Unit,
) {
    val navController = rememberNavController()
    val startDestination = if (userAuthorized) MAIN_NAV_GRAPH_ROUTE else AUTH_NAV_GRAPH_ROUTE
    NavHost(navController = navController, startDestination = startDestination) {
        authGraph(navController, AUTH_NAV_GRAPH_ROUTE, verifyPhoneNumber)
        mainGraph(navController, MAIN_NAV_GRAPH_ROUTE, showSnackbar)
    }
}

private const val AUTH_NAV_GRAPH_ROUTE = "auth"
private const val MAIN_NAV_GRAPH_ROUTE = "main"