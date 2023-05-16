package com.ewingelen.chatter.core.presentation.navigation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
fun makeDestinationRoute(route: String, vararg args: String) =
    route + args.joinToString { arg -> "/{$arg}" }

fun makeNavigationRoute(route: String, vararg args: Any) =
    route + args.joinToString { arg -> "/$arg" }