package com.atg.tbs.auth.verify

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.atg.tbs.auth.common.GoBack

enum class VerifyType {
    LOGIN, SIGN_IN, RESTORE_PASSWORD
}

data class VerifyScreen(private val type: VerifyType) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<VerifyScreenModel>()
        VerificationCodeScreen(screenModel.props.value, type)
    }
}

@Composable
private fun VerificationCodeScreen(props: VerifyProps, type: VerifyType) {
    var code by remember { mutableStateOf(List(4) { "" }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navigator = LocalNavigator.currentOrThrow

        GoBack { navigator.pop() }

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Verification code",
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "We have sent the code verification\nto email",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            code.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = {
                        if (it.length <= 1) {
                            code = code.toMutableList().also { list -> list[index] = it }
                        }
                    },
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { /* Resend */ },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text("Resend")
            }

            Button(
                onClick = { props.codeConfirmBound.invoke(code.joinToString(separator = ""), type) },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
            ) {
                Text("Confirm")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        DigitKeyboard(
            onDigitClick = { digit ->
                val digitIndex = code.indexOfFirst { it.isEmpty() }
                if (digitIndex < 0) return@DigitKeyboard
                code = code.mapIndexed { index, s -> if (index == digitIndex) digit else s }
            },
            onDeleteClick = {
                val digitIndex = code.indexOfLast { it.isNotEmpty() }.takeIf { it >= 0 } ?: 3
                code = code.mapIndexed { index, s -> if (index == digitIndex) "" else s }
            }
        )
    }
}

@Composable
private fun DigitKeyboard(
    onDigitClick: (String) -> Unit,
    onDeleteClick: () -> Unit
) {
    val digits = remember {
        listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("", "0", "⌫")
        )
    }

    Column(
        modifier = Modifier.padding(60.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        digits.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { label ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .clickable(enabled = label.isNotEmpty()) {
                                when (label) {
                                    "⌫" -> onDeleteClick()
                                    "" -> {}
                                    else -> onDigitClick(label)
                                }
                            }
                            .background(Color(0xFFF0F0F0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = label, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}
