package com.example.promuldismov_tareatiendavirtual

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.promuldismov_tareatiendavirtual.data.TiendaViewModel
import com.example.promuldismov_tareatiendavirtual.screens.ConfirmationScreen
import com.example.promuldismov_tareatiendavirtual.screens.DetailsScreen
import com.example.promuldismov_tareatiendavirtual.screens.HomeScreen
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object Home : Routes()

    @Serializable
    data object Details : Routes()

    @Serializable
    data object Confirmation : Routes()
}

@Composable
fun AppNavigation(padding: PaddingValues, viewModel: TiendaViewModel = viewModel()) {


    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Home> {
                HomeScreen(
                    onProductClick = { backStack.add(Routes.Details) })
            }
            entry<Routes.Details> {
                DetailsScreen(
                    onBuyClick = { backStack.add(Routes.Confirmation) },
                    viewModel = viewModel
                )
            }
            entry<Routes.Confirmation> {
                ConfirmationScreen(
                    onBackToHomeClick = {
                        backStack.clear()
                        backStack.add(Routes.Home)
                    }
                )
            }
        }
    )
}