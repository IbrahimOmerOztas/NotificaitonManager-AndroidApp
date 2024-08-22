package com.oztasibrahimomer.firebase_fcm_cloud_functions.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Auth_Page(navController: NavController) {
    val context= LocalContext.current
    val auth=FirebaseAuth.getInstance()
    var showDialog = remember { mutableStateOf(false) }
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    val currentUser = auth.currentUser
    if (currentUser != null) {
        navController.navigate("main")
    }

    LaunchedEffect(key1 = Unit){
        getFcmToken {
            it?.let {

                Log.i("Token",it)

            }
        }
    }


    if(showDialog.value){
        KullaniciKAydiDialog(showDialog,context)
    }


    Column(
        modifier=Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        Text(text = "Kullanıcı Giris Ekrani", fontSize = 43.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(30.dp))

        TextField(value =email, onValueChange ={email=it}, label = { Text(text = "Email", color = Color.Gray)})
        Spacer(modifier = Modifier.height(15.dp))
        TextField(value =password, onValueChange ={password=it}, label = { Text(text = "Password", color = Color.Gray)}, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.width(300.dp), horizontalArrangement = Arrangement.Center){
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    if(email.isBlank() || password.isBlank()){
                        Toast.makeText(context,"Email veya Password alanını boş bırakma",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        auth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener {
                                navController.navigate("main")

                            }
                            .addOnFailureListener {exception->
                                Toast.makeText(context,"$exception",Toast.LENGTH_SHORT).show()


                            }
                        }

                }) {
                Text(text = "Giris Yap")

            }
            Button(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    showDialog.value=true
                }) {
                Text(text ="Kayit Ol")

            }

        }

    }

}