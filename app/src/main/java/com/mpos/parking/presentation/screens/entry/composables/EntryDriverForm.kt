package com.mpos.parking.presentation.screens.entry.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mpos.parking.R
import com.mpos.parking.presentation.composables.TextFieldInput

@Composable
fun EntryDriverForm(
    name: String,
    dni: String,
    phone: String,
    onNameChanged: (String) -> Unit,
    onDniChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onContinueClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_driver), Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.outline
        )

        TextFieldInput(
            value = name,
            label = stringResource(R.string.name),
            onTextChanged = onNameChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Person,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = dni,
            label = stringResource(R.string.dni),
            onTextChanged = onDniChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Badge,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = phone,
            label = stringResource(R.string.movil),
            onTextChanged = onPhoneChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Phone,
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        )

        Button(
            onClick = onContinueClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = name.isNotBlank() && dni.isNotBlank() && phone.isNotBlank()
        ) {
            Text(text = stringResource(R.string.continues))
        }
    }
}