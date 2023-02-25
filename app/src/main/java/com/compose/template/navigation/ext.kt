package com.compose.template.navigation

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.compose.template.navigation.ArgumentPairs.argumentName
import com.compose.template.navigation.ArgumentPairs.defaultValue
import com.compose.template.utills.parcelable

@Composable
fun NavHostController.getCallback(key: String) =
    currentBackStackEntry?.savedStateHandle?.getLiveData<String>(key)?.observeAsState()?.value

@Composable
fun NavBackStackEntry.getCallbackOrArg(navController: NavHostController, key: String) =
    navController.getCallback(key) ?: getArg(key)

fun NavBackStackEntry.getArg(key: String) = arguments?.parcelable<Parcelable>(key)

fun createNavArgument(vararg args: Pair<String, Any>): MutableList<NamedNavArgument> {
    val argumentList = mutableListOf<NamedNavArgument>()
    args.forEach {
        argumentList.add(navArgument(it.argumentName) {
            type = NavType.inferFromValueType(it.defaultValue)
            defaultValue = it.defaultValue
        })
    }

    return argumentList
}

fun NavBackStackEntry.getArgument(arg: Pair<String, Any>) =
    (arguments?.parcelable(arg.argumentName) ?: arg.defaultValue).toString()

fun NavHostController.popBackWithArgs(key: String, arg: String) =
    previousBackStackEntry?.savedStateHandle?.set(key, arg).apply { popBackStack() }