package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SnakeAlertDialogContent(
	shape: Shape = AlertDialogDefaults.shape,
	textContentColor: Color = AlertDialogDefaults.textContentColor,
	buttonContentColor: Color = MaterialTheme.colorScheme.primary,
	buttons: @Composable () -> Unit,
	text: @Composable () -> Unit
) {

	Dialog(onDismissRequest = {}) {
		Surface(
			shape = shape,
			tonalElevation = 6.dp  // Level 3
		) {
			Column(
				modifier = Modifier
					.sizeIn(minWidth = MinWidth, maxWidth = MaxWidth)
					.padding(DialogPadding)
			) {
				CompositionLocalProvider(LocalContentColor provides textContentColor) {
					ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
						Box(
							Modifier
								.weight(weight = 1f, fill = false)
								.padding(TextPadding)
								.align(Alignment.CenterHorizontally)
						) {
							text()
						}
					}
				}
				
				Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
					CompositionLocalProvider(LocalContentColor provides buttonContentColor) {
						ProvideTextStyle(
							value = MaterialTheme.typography.labelLarge,
							content = buttons
						)
					}
				}
			}
		}
	}
}

// Paddings for each of the dialog's parts.
private val DialogPadding = PaddingValues(all = 24.dp)
private val TextPadding = PaddingValues(bottom = 24.dp)

private val MinWidth = 280.dp
private val MaxWidth = 560.dp
