package com.compose.template.navigation

object EndPoint {
}

object ArgumentPairs {
    val <ArgumentName> Pair<ArgumentName, *>.argumentName get() = first
    val <DefaultValue> Pair<*, DefaultValue>.defaultValue get() = second
}

object QueryParams {
    val ID = Pair("id", "0")
}


sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Settings : Screens("settings") {
        fun settingsWithIdRoute() = "${Settings.route}?id={id}"
    }

    object ProfileSettings : Screens("settings_profile")
}