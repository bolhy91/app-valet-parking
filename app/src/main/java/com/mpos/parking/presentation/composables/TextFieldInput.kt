package com.mpos.parking.presentation.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldInput(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    imeAction: ImeAction = ImeAction.Next,
    placeholder: String? = null,
    trailingIcon: ImageVector? = null,
    leadingIcon: ImageVector? = null,
    onTextChanged: (text: String) -> Unit,
) {
    TextField(
        value = value,
        enabled = enabled,
        readOnly = readOnly,
        onValueChange = onTextChanged,
        modifier = modifier
            .semantics {
                if (isError) error(errorMessage)
            },
        label = { Text(label) },
        placeholder = { placeholder?.let { Text(it) } },
        singleLine = singleLine,
        isError = isError,
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "$label leading Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else null,
        trailingIcon = if (trailingIcon != null) {
            {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "$label trailing Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else null,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction,
            capitalization = keyboardCapitalization
        ),
        supportingText = {
            Text(
                text = if (isError) errorMessage else "",
                modifier = Modifier.clearAndSetSemantics { },
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}