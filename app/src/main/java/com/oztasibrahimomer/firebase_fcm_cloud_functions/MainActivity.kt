package com.oztasibrahimomer.firebase_fcm_cloud_functions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oztasibrahimomer.firebase_fcm_cloud_functions.screen.Auth_Page
import com.oztasibrahimomer.firebase_fcm_cloud_functions.screen.Main_Screen
import com.oztasibrahimomer.firebase_fcm_cloud_functions.ui.theme.Firebase_FCM_CloudFunctionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Firebase_FCM_CloudFunctionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    SayfaGecis()



                }
            }
        }
    }
}

@Composable
fun SayfaGecis() {

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "giris"){

        composable("giris"){
            Auth_Page(navController = navController)

        }
        composable("main"){
            Main_Screen(navController)

        }
    }

}

