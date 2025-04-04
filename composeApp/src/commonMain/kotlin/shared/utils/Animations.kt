package shared.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

fun animatedNavTransitions(animationDuration: Int = 300): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition {
    return { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(animationDuration)) }
}

fun animatedNavExitTransitions(animationDuration: Int = 300): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition {
    return { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(animationDuration)) }
}

fun animatedPopEnter(animationDuration: Int = 300): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition {
    return { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(animationDuration)) }
}

fun animatedPopExit(animationDuration: Int = 300): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition {
    return { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(animationDuration)) }
}