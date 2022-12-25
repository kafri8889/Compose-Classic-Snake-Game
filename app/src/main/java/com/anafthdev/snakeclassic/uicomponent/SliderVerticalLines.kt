package com.anafthdev.snakeclassic.uicomponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
@Composable
fun SliderVerticalLines(
	labels: List<String>,
	lineHeight: Dp,
	modifier: Modifier = Modifier,
	rem: Int = 2,
	lineColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
	
	val textMeasurer = rememberTextMeasurer()
	
	val labelTextStyle = MaterialTheme.typography.labelMedium.copy(
		fontWeight = FontWeight.Normal
	)

	Box(modifier = modifier) {
		Canvas(
			modifier = Modifier
				.matchParentSize()
		) {
			val drawPadding = 10.dp.toPx()
			val mLineHeight = lineHeight.toPx()
			val labelBottomPadding = 8.dp.toPx()
			
			// Center vertically
			val availableSpace = size.height - mLineHeight
			val yStart = availableSpace / 2
			val yEnd = mLineHeight + yStart
			// Center vertically

			val distance = (size.width - (2 * drawPadding)) / labels.lastIndex

			labels.forEachIndexed { i, label ->
				drawLine(
					color = lineColor,
					start = Offset(x = drawPadding + i.times(distance), y = yStart),
					end = Offset(x = drawPadding + i.times(distance), y = yEnd)
				)
				
				// Draw label
				if (i.rem(rem) == 1) {
					val topLeft = Offset(
						x = drawPadding + i.times(distance),
						y = 0f - labelBottomPadding
					)
					
					val maxSize = IntSize(
						width = ceil(this.size.width - topLeft.x).roundToInt(),
						height = ceil(this.size.height - topLeft.y).roundToInt()
					)
					
					val textLayoutResult = textMeasurer.measure(
						text = AnnotatedString(label),
						style = labelTextStyle,
						overflow = TextOverflow.Clip,
						softWrap = true,
						maxLines = 1,
						constraints = Constraints(maxWidth = maxSize.width, maxHeight = maxSize.height),
						layoutDirection = layoutDirection,
						density = this
					)
					
					drawText(
						text = label,
						maxLines = 1,
						textMeasurer = textMeasurer,
						style = labelTextStyle,
						topLeft = Offset(
							// Center text horizontally with line x position
							x = topLeft.x - textLayoutResult.size.width / 2,
                            y = topLeft.y
						)
					)
				}
			}
		}
	}
}
