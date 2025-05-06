package com.atg.tbs.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.auth.register.RegistrationScreen
import com.atg.tbs.auth.restore.byEmail.ByEmailScreen
import com.atg.tbs.auth.verify.VerifyScreen
import com.atg.tbs.auth.verify.VerifyType

object  LoginScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<LoginScreenModel>()
        val effect by screenModel.effect.collectAsState(null)
        val navigator = LocalNavigator.currentOrThrow
        LoginScreenView(screenModel.props.value)

        LaunchedEffect(effect) {
            if (effect == null) return@LaunchedEffect
            when(effect) {
                is ConfirmLoginRoute -> navigator.push(VerifyScreen(VerifyType.LOGIN))
                is SignInRoute -> navigator.push(RegistrationScreen)
                is ForgotPasswordRoute -> navigator.push(ByEmailScreen)
            }
        }
    }
}


@Composable
fun LoginScreenView(props: LoginProps) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { props.forgotPasswordBound.invoke(email) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Forgot password?")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { props.loginBound.invoke(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(
            onClick = { props.signUpBound.invoke()  },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Sign Up!")
        }
    }
}