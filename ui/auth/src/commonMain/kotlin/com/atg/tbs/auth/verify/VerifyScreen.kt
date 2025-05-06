package com.atg.tbs.auth.verify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

enum class VerifyType {
    LOGIN, SIGN_IN, RESTORE_PASSWORD
}

data class VerifyScreen(private val type: VerifyType) : Screen {
    @Composable
    override fun Content() {
        val a = koinScreenModel<VerifyScreenModel>()

        var password by remember { mutableStateOf("") }

        Column {

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Button(
                onClick = { a.confirm(password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }


    }
}