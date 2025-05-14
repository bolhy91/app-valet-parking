package com.mpos.parking.presentation.screens.entry.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.DriveFileRenameOutline
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mpos.parking.R
import com.mpos.parking.presentation.composables.TextFieldInput

@Composable
fun EntryVehicleForm(
    license: String,
    brand: String,
    model: String,
    color: String,
    position: String,
    observation: String,
    onLicenseChanged: (String) -> Unit,
    onBrandChanged: (String) -> Unit,
    onModelChanged: (String) -> Unit,
    onColorChanged: (String) -> Unit,
    onPositionChanged: (String) -> Unit,
    onObservationChanged: (String) -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_vehicler),
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.outline
        )

        TextFieldInput(
            value = license,
            label = stringResource(R.string.place),
            onTextChanged = onLicenseChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.DriveFileRenameOutline,
            keyboardCapitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = brand,
            label = stringResource(R.string.brand),
            onTextChanged = onBrandChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.DirectionsCar,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = model,
            label = stringResource(R.string.model),
            onTextChanged = onModelChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.DirectionsCar,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = color,
            label = stringResource(R.string.color),
            onTextChanged = onColorChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.ColorLens,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = position,
            label = "Posición (opcional)",
            onTextChanged = onPositionChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.LocationOn,
            imeAction = ImeAction.Next
        )

        TextFieldInput(
            value = observation,
            label = "Observaciones (opcional)",
            onTextChanged = onObservationChanged,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Outlined.Notes,
            imeAction = ImeAction.Done
        )

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = license.isNotBlank() && brand.isNotBlank() && model.isNotBlank() && color.isNotBlank()
        ) {
            Text(text = "Registrar Entrada")
        }
    }
}