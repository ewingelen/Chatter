package com.ewingelen.chatter.core.presentation.navigation


fun makeDestinationRouteWithArgs(route: String, vararg args: Any) =
    route + args.joinToString { arg -> "/{$arg}" }

fun makeNavigationRouteWithArgs(route: String, vararg args: Any) =
    route + args.joinToString { arg -> "/$arg" }