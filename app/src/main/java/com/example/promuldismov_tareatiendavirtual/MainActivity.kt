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
import com.example.promuldismov_tareatiendavirtual.ui.theme.ProMulDisMov_TareaTiendaVirtualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProMulDisMov_TareaTiendaVirtualTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(padding =  innerPadding)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProMulDisMov_TareaTiendaVirtualTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavigation(padding =  innerPadding)
        }
    }
}