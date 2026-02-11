package com.example.promuldismov_tareatiendavirtual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.promuldismov_tareatiendavirtual.navigation.AppNavigation
import com.example.promuldismov_tareatiendavirtual.ui.theme.ProMulDisMov_TareaTiendaVirtualTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        enableEdgeToEdge()
        setContent {
            ProMulDisMov_TareaTiendaVirtualTheme {
                AppNavigation(auth)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProMulDisMov_TareaTiendaVirtualTheme {
        AppNavigation(Firebase.auth)
    }
}