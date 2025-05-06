package com.atg.tbs.auth.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun GoBack(click: () -> Unit) {
    Box(modifier = Modifier.clickable { click.invoke() }) {
        Icon(
            modifier = Modifier.padding(32.dp),
            imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back Icon",
            tint = MaterialTheme.colors.primary
        )
    }
}
