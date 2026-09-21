package com.example.declarativecomposeapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext


@Composable
fun EventStateTest() {
    var count by remember {
        mutableStateOf(0)
    }

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Counter : $count")

        Button(
            onClick = {
                count++
                Log.d("TEST", "Count : $count")
            }
        ) {
            Text("Tambah")
        }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                Log.d("TEST", "Text berubah : $it")
            },
            label = {
                Text("Masukkan teks")
            }
        )

        Text(text = "Teks: $text")
    }
}

@Composable
fun ImplicitIntentTest() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Buka Browser")
        }

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:081234567890")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Buka Telepon")
        }

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("contoh@email.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Test Email")
                    putExtra(Intent.EXTRA_TEXT, "Halo, ini percobaan email.")
                }

                context.startActivity(
                    Intent.createChooser(intent, "Pilih aplikasi email")
                )
            }
        ) {
            Text("Buka Email")
        }

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=Malang")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Buka Maps")
        }
    }
}