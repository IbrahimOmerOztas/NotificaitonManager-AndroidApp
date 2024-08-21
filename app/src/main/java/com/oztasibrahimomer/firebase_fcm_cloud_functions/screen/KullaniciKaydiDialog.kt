package com.oztasibrahimomer.firebase_fcm_cloud_functions.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KullaniciKAydiDialog(showDialog:MutableState<Boolean>,context: Context) {

    val auth=FirebaseAuth.getInstance()
    var email=remember{ mutableStateOf("") }
    var password=remember{ mutableStateOf("") }

    AlertDialog(
        modifier = Modifier.size(300.dp,350.dp),
        onDismissRequest = {
                           showDialog.value=false
        },
        title = {
            Text(text ="Kullanici Kaydi", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
        },
        text = {
               Column {
                   TextField(value = email.value, onValueChange = {email.value=it}, label = { Text(text = "Email")})
                   Spacer(modifier = Modifier.height(15.dp))
                   TextField(value = password.value, onValueChange = {password.value=it}, label = { Text(text = "Password")})
               }



        },
        confirmButton = {
            Button(onClick = {
                if (email.value.isBlank() || password.value.isBlank()){
                    Log.i("Yarrak","Bos birakma!!!")
                }
                else{
                    auth.createUserWithEmailAndPassword(email.value,password.value)
                        .addOnSuccessListener {
                            getFcmToken { token->
                                token?.let {
                                    Toast.makeText(context,"Token: $token",Toast.LENGTH_SHORT).show()

                                }

                            }
                            showDialog.value=false

                        }
                        .addOnFailureListener { exception->

                            Toast.makeText(context,"$exception",Toast.LENGTH_SHORT).show()
                        }
                }

            }) {
                Text(text = "Kayit Ol")
            }

        }


    )
}

private fun getFcmToken(onComplete: (String?) -> Unit) {
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val token = task.result
            onComplete(token)
        } else {
            onComplete(null)
        }
    }
}