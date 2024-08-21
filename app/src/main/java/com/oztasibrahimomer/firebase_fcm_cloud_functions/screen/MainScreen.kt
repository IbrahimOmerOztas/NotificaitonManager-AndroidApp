package com.oztasibrahimomer.firebase_fcm_cloud_functions.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main_Screen(navController: NavController) {
    val auth=FirebaseAuth.getInstance()

    Scaffold(
        topBar = {
                 TopAppBar(title = { Text(text = "Main Screen") },
                     actions = {
                         IconButton(onClick = {
                             auth.signOut()
                             navController.navigate("giris"){
                                 popUpTo("main"){inclusive=true}
                             }
                         }
                         ) {
                             Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")

                 }

                     }
                 )
        },
        
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Main Screen", fontSize = 50.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)

            }
        }
    )

}