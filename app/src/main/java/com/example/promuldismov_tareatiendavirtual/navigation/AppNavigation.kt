package com.example.promuldismov_tareatiendavirtual.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.promuldismov_tareatiendavirtual.ui.screens.AddProductScreen
import com.example.promuldismov_tareatiendavirtual.viewmodel.ShopViewModel
import com.example.promuldismov_tareatiendavirtual.ui.screens.ConfirmationScreen
import com.example.promuldismov_tareatiendavirtual.ui.screens.DetailsScreen
import com.example.promuldismov_tareatiendavirtual.ui.screens.HomeScreen
import com.example.promuldismov_tareatiendavirtual.ui.screens.LoginScreen
import com.example.promuldismov_tareatiendavirtual.ui.screens.RegisterScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    auth: FirebaseAuth,
    viewModel: ShopViewModel = viewModel()
) {


    val backStack = rememberNavBackStack(Routes.Login)

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
            viewModel.cleanState()
        },
        entryProvider = entryProvider {
            entry<Routes.Login> {
                LoginScreen(
                    auth = auth,
                    onNavigateToRegister = { backStack.add(Routes.Register) },
                    onNavigateToHome = { backStack.add(Routes.Home) }
                )
            }
            entry<Routes.Register> {
                RegisterScreen(
                    auth = auth,
                    onNavigateToLogin = { backStack.add(Routes.Login) },
                    onNavigateToHome = { backStack.add(Routes.Home) }
                )
            }
            entry<Routes.Home> {
                HomeScreen(
                    onProductClick = { backStack.add(Routes.Details) },
                    onAddProductClick = {
                        backStack.add(Routes.AddProduct)
                        viewModel.cleanState()
                    },
                    onNavigateToLogin = {
                        backStack.clear()
                        backStack.add(Routes.Login)
                    }
                )
            }
            entry<Routes.Details> {
                DetailsScreen(
                    onBuyClick = { backStack.add(Routes.Confirmation) },
                    viewModel = viewModel,
                    onNavigateToLogin = {
                        backStack.clear()
                        backStack.add(Routes.Login)
                    }
                )
            }
            entry<Routes.Confirmation> {
                ConfirmationScreen(
                    onBackToHomeClick = {
                        backStack.clear()
                        backStack.add(Routes.Home)
                    },
                    onNavigateToLogin = {
                        backStack.clear()
                        backStack.add(Routes.Login)
                    }
                )
            }
            entry<Routes.AddProduct> {
                AddProductScreen(
                    onView = { backStack.add(Routes.Details) },
                    onNavigateToLogin = {
                        backStack.clear()
                        backStack.add(Routes.Login)
                    }
                )
            }
        }
    )
}