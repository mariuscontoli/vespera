package com.absurddevs.vespera.login.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import com.absurddevs.vespera.ui.theme.VesperaTheme
import kotlin.random.Random

@Composable
fun RandomLinesCanvas() {
    var lines by remember { mutableStateOf(emptyList<Pair<Float, Float>>()) }

    // Generate random lines when the composable is first drawn
    DisposableEffect(Unit) {
        lines = generateRandomLines()
        onDispose { }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        drawLines(lines, color = Color.White)
    }
}

fun DrawScope.drawLines(lines: List<Pair<Float, Float>>, color: Color) {
    lines.forEach { (startX, startY) ->
        val endX = startX + Random.nextFloat() * size.height // adjust line length as needed
        val endY = startY + Random.nextFloat() * size.height
        val startOffset = Offset(startX, startY)
        val endOffset = Offset(endX, endY)
        drawLine(color, start = startOffset, end = endOffset)
    }
}

fun generateRandomLines(): List<Pair<Float, Float>> {
    val lines = mutableListOf<Pair<Float, Float>>()
    repeat(50) { // Adjust the number of lines as needed
        lines.add(Pair(Random.nextFloat(), Random.nextFloat()))
    }
    return lines
}

@Preview(showBackground = true)
@Composable
fun RandomLinesCanvasPreview() {
    VesperaTheme {
        RandomLinesCanvas()
    }
}