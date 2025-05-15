package com.mpos.parking.presentation.screens.entry.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.DriveFileRenameOutline
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import com.mpos.parking.domain.models.Parking
import com.mpos.parking.presentation.composables.TextFieldInput
import com.mpos.parking.presentation.screens.entry.composables.ParkingSpotSelector

@Composable
fun EntryVehicleForm(
    license: String,
    brand: String,
    model: String,
    color: String,
    position: String,
    availableParkingSpots: List<Parking>,
    isLoading: Boolean,
    error: String?,
    licenseError: String?,
    isCheckingLicense: Boolean,
    onLicenseChanged: (String) -> Unit,
    onBrandChanged: (String) -> Unit,
    onModelChanged: (String) -> Unit,
    onColorChanged: (String) -> Unit,
    onPositionChanged: (String) -> Unit,
    onRetryLoadingSpots: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
            imeAction = ImeAction.Next,
            isError = licenseError != null,
            errorMessage = licenseError ?: "",
            trailingIcon = if (isCheckingLicense) Icons.Outlined.DirectionsCar else null
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
            imeAction = ImeAction.Done
        )

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(onClick = onRetryLoadingSpots) {
                    Text(stringResource(R.string.retry))
                }
            }
        } else {
            ParkingSpotSelector(
                availableSpots = availableParkingSpots,
                selectedSpot = position,
                onSpotSelected = onPositionChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = license.isNotBlank() && 
                    licenseError == null &&  // Deshabilitamos el botón si hay error en la placa
                    brand.isNotBlank() && 
                    model.isNotBlank() && 
                    color.isNotBlank() && 
                    position.isNotBlank() &&
                    !isCheckingLicense  // Deshabilitamos el botón mientras se verifica la placa
        ) {
            Text(text = stringResource(R.string.register_entry))
        }
    }
}