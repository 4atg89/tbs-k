package com.atg.tbs.auth.restore.byEmail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.auth.common.GoBack
import com.atg.tbs.auth.verify.VerifyScreen
import com.atg.tbs.auth.verify.VerifyType

object ByEmailScreen: Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ByEmailScreenModel>()
        val effect by screenModel.effect.collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        ByEmailScreenView(screenModel.props.value)

        LaunchedEffect(effect) {
            if (effect == null) return@LaunchedEffect
            when (effect) {
                is ConfirmRestoreRoute -> navigator.push(VerifyScreen(VerifyType.RESTORE_PASSWORD))
                is BackRoute -> navigator.pop()
            }
        }
    }
}

@Composable
private fun ByEmailScreenView(props: ByEmailProps) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        GoBack { props.backBound.invoke() }

        Text(
            text = "Forgot Password",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { props.restoreBound(email) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = email.isNotBlank()
        ) {
            Text("Send Reset Link")
        }
    }
}
