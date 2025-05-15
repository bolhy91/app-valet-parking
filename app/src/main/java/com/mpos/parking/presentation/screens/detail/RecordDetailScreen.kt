package com.mpos.parking.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mpos.parking.R
import com.mpos.parking.domain.models.RecordWithDetails
import com.mpos.parking.presentation.composables.BottomSheet
import com.mpos.parking.presentation.screens.detail.composables.InfoRow
import com.mpos.parking.presentation.screens.detail.composables.SectionHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordDetailScreen(
    record: RecordWithDetails,
    onDismiss: () -> Unit,
    viewModel: RecordDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(record) {
        viewModel.onEvent(RecordDetailEvent.Initialize(record))
    }

    LaunchedEffect(state.successMessage) {
        state.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onEvent(RecordDetailEvent.DismissMessage)
            onDismiss()
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onEvent(RecordDetailEvent.DismissDialog)
        }
    }

    BottomSheet(
        sheetState = sheetState,
        onDismiss = onDismiss,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.padding(bottom = 32.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.detalles_estacionamiento),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                SectionHeader(title = stringResource(R.string.detalles_vehiculo))
                state.record?.let { record ->
                    InfoRow(
                        label = stringResource(
                            R.string.vehicle_name,
                            record.vehicleBrand,
                            record.vehicleModel
                        ),
                        value = stringResource(R.string.plate_name, record.vehicleLicense)
                    )
                    InfoRow(
                        label = stringResource(R.string.color),
                        value = record.vehicleColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SectionHeader(title = stringResource(R.string.title_driver))
                    InfoRow(
                        label = stringResource(R.string.name),
                        value = record.driverName
                    )
                    InfoRow(
                        label = stringResource(R.string.movil),
                        value = record.driverPhone
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SectionHeader(title = stringResource(R.string.parking))
                    InfoRow(
                        label = stringResource(R.string.start_time),
                        value = record.formatDateTime(record.entryTime)
                    )
                    record.exitTime?.let {
                        InfoRow(
                            label = stringResource(R.string.end_time),
                            value = record.formatDateTime(it)
                        )
                    }
                    InfoRow(
                        label = stringResource(R.string.puesto),
                        value = record.parkingNumber
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SectionHeader(title = stringResource(R.string.detalles_costo))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.tiempo_estacionado),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = state.parkingDuration,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.costo_total),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Text(
                            text = "$${state.parkingCost}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    if (record.exitTime == null) {
                        Button(
                            onClick = { viewModel.onEvent(RecordDetailEvent.ProcessExit) },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !state.isExitProcessing
                        ) {
                            if (state.isExitProcessing) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text(text = stringResource(R.string.procesar_salida))
                            }
                        }
                    }
                }
            }

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) { data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    containerColor = if (state.error != null)
                        MaterialTheme.colorScheme.errorContainer
                    else
                        MaterialTheme.colorScheme.primaryContainer,
                    contentColor = if (state.error != null)
                        MaterialTheme.colorScheme.onErrorContainer
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer,
                ) {
                    Text(text = data.visuals.message)
                }
            }
        }
    }
}