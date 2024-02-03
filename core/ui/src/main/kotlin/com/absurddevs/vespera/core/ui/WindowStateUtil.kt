package com.absurddevs.vespera.core.ui

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Interface that holds information about the [DevicePosture]
 * of the current device.
 *
 * The posture can either be [NormalPosture], [BookPosture] or [Separating], each representing
 * how the device screen is presented to the user.
 *
 * Usually, both [BookPosture] and [Separating] represents a foldable device.
 */
sealed interface DevicePosture {
    data object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        val orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

@OptIn(ExperimentalContracts::class)
fun isBookPosture(
    foldingFeature: FoldingFeature?
): Boolean {
    contract { returns(true) implies (foldingFeature != null) }
    return foldingFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(
    foldingFeature: FoldingFeature?
): Boolean {
    contract { returns(true) implies (foldingFeature != null) }
    return foldingFeature?.state == FoldingFeature.State.FLAT && foldingFeature.isSeparating
}